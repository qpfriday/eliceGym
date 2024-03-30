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

        // db 에 이미 동일한 username 을 가진 회원이 존재하는지
        boolean isUser = userRepository.existsByLoginId(joinDto.getLoginId());
        if (isUser) {
            return;
        }

        UserEntity data = new UserEntity();

        data.setLoginId(joinDto.getLoginId());
        data.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword())); // 필수적으로 BCrypt 암호화 진행
        data.setName(joinDto.getName());
        data.setEmail(joinDto.getEmail());
        data.setRoll("ROLE_ADMIN"); // service 에서 user 역할 부여
        data.setCreatedAt(joinDto.getCreatedAt());
        data.setUpdatedAt(joinDto.getUpdatedAt());
        userRepository.save(data);
    }
}
