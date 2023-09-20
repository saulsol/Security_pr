package com.example.springsecurity_pr.userService;

import com.example.springsecurity_pr.domain.UserEntity;
import com.example.springsecurity_pr.dto.UserCreateDto;
import com.example.springsecurity_pr.exception.AlreadyExistIdException;
import com.example.springsecurity_pr.repository.UserRepository;
import com.example.springsecurity_pr.util.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;




    public void create(UserCreateDto userCreateDto){
        final String userName = userCreateDto.getUsername();
        if(userRepository.existsByUsername(userName)){
            log.warn("이미 존재하는 ID");
            throw new AlreadyExistIdException(ErrorCode.ALREADY_EXISTS);
        }

        userRepository.save(userCreateDto.toEntity());

    }

}
