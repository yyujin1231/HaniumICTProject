package com.example.packpagedosirak.api;


import com.example.packpagedosirak.dto.UserDTO;
import com.example.packpagedosirak.entity.User;
import com.example.packpagedosirak.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping("/join")
    public String joinProcess(UserDTO userDTO) {

        System.out.println(userDTO.getUsername());
        userService.joinProcess(userDTO);

        return "welcome";
    }

    @GetMapping("/admin")
    public String adminP() {

        return "UserApiController";
    }

    @GetMapping("/user/profile")
    public UserDTO getUserProfile(Authentication authentication) {
        String username = authentication.getName(); // 현재 인증된 사용자의 username
        User user = userService.findByUsername(username); // 사용자 서비스를 통해 사용자 정보 조회

        // User 엔티티를 UserDTO로 변환 (실제 구현에 따라 필요한 정보를 설정)
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        // userDTO.setPassword(user.getPassword()); // 보안상의 이유로 패스워드는 전송하지 않는 것이 좋음
        // 다른 필요한 정보를 userDTO에 설정

        return userDTO;
    }












}
