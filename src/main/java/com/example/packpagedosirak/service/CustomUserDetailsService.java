package com.example.packpagedosirak.service;

import com.example.packpagedosirak.dto.CustomUserDetails;
import com.example.packpagedosirak.entity.User;
import com.example.packpagedosirak.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    //데이터베이스에 접근할 userRepository
    private final UserRepository userRepository;

    //그 객체를 초기화시켜주기 위해서 주입받을 생성자
    public CustomUserDetailsService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //DB에서 조회
        User userData = userRepository.findByUsername(username);

        if (userData != null) {

            //UserDetails에 담아서 return하면 AutneticationManager가 검증 함
            return new CustomUserDetails(userData);
        }

        return null;
    }
}