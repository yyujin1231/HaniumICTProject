package com.example.packpagedosirak.config;

import com.example.packpagedosirak.jwt.JWTFilter;
import com.example.packpagedosirak.jwt.JWTUtil;
import com.example.packpagedosirak.jwt.LoginFilter;
import com.example.packpagedosirak.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입

    //Spring Security의 인증 매커니즘 구성에 사용
    private final AuthenticationConfiguration authenticationConfiguration;

    //JWT 생성 및 검증을 위한 유틸리티 클래스
    private final JWTUtil jwtUtil;

    private final CustomOAuth2UserService customOAuth2UserService;



    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil, CustomOAuth2UserService customOAuth2UserService) {

        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
        this.customOAuth2UserService = customOAuth2UserService; //주입받아서 해당 field변수에다가 초기화 진행
    }


    //AuthenticationManager Bean 등록 이는 인증 프로세스를 주로 관리하는 인터페이스
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //csrf disable csrf공격방어
        http
                .csrf((auth) -> auth.disable());

        //From 로그인 방식 able
        http
                //.formLogin((auth) -> auth.defaultSuccessUrl("/"));
                .formLogin((auth) -> auth.disable());


        //http basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());

        //경로별 인가 작업 /login, /, join 경로는 모두에게 허용, /adim경로는 "ADMIN"을 가진 사람에게만
        //http
                //.authorizeHttpRequests((auth) -> auth
                //        .requestMatchers("/login", "/", "/join").permitAll()
                //        .requestMatchers("/admin").hasRole("ADMIN")
                //        .requestMatchers("/abc").hasRole("ADMIN")
                //        .anyRequest().authenticated());

        //oauth2
        http
                .oauth2Login((oauth2) -> oauth2
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                                .userService(customOAuth2UserService)));

        //경로별 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/").permitAll() //"/"경로에 대해서는 모두가 접근가능
                        .anyRequest().authenticated()); //"나머지 경로에 대해서는 로그인한 사용자만 접근할 수 있는 authenticated접군

        //AuthenticationManager()와 JWTUtil 인수 전달
        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);
        //JWTFilter 등록
        http
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);


        //세션 설정(stateless상태로..이게 젤 중요..) JWT발급후 JWT통해 인증인가할거기때문에
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));



        return http.build();


    }
}