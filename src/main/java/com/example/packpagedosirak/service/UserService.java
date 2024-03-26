package com.example.packpagedosirak.service;


import com.example.packpagedosirak.dto.UserDTO;
import com.example.packpagedosirak.entity.User;
import com.example.packpagedosirak.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(UserDTO userDTO) {

        String username = userDTO.getUsername();
        //String password = userDTO.getPassword();

        Boolean isExist = userRepository.existsByUsername(username);

        if (isExist) {

            return;
        }

        User data = new User();

        data.setUsername(username);
        //data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setRole("ROLE_ADMIN");

        userRepository.save(data);
    }

    // username을 이용하여 User 객체를 찾는 메소드
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
