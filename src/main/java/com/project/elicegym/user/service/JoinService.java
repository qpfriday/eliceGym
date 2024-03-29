package com.project.elicegym.user.service;

import com.project.elicegym.user.dto.JoinDto;
import com.project.elicegym.user.entity.UserEntity;
import com.project.elicegym.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDto joinDto) { // boolean 으로 해서 회원가입 성공, 실패를 반환해도 된다



        UserEntity data = new UserEntity();

        data.setUsername(joinDto.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword())); // 필수적으로 BCrypt 암호화 진행
        data.setRoll("ROLE_USER"); // service 에서 user 역할 부여

        userRepository.save(data);
    }
}
