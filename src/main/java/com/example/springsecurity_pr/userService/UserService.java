package com.example.springsecurity_pr.userService;

import com.example.springsecurity_pr.domain.UserEntity;
import com.example.springsecurity_pr.dto.LoginSuccessDto;
import com.example.springsecurity_pr.dto.UserCreateDto;
import com.example.springsecurity_pr.exception.AlreadyExistIdException;
import com.example.springsecurity_pr.jwt.TokenProvider;
import com.example.springsecurity_pr.repository.UserRepository;
import com.example.springsecurity_pr.util.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    public String create(UserCreateDto userCreateDto){
        final String userName = userCreateDto.getUsername();
        if(userRepository.existsByUsername(userName)){
            log.warn("이미 존재하는 ID");
            throw new AlreadyExistIdException(ErrorCode.ALREADY_EXISTS);
        }
        UserEntity createdUser = userRepository.save(userCreateDto.toEntity(passwordEncoder));
        return createdUser.getUsername();
    }


    public LoginSuccessDto getByCredentials(final String username, final String password){
        final UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity != null && passwordEncoder.matches(password, userEntity.getPassword())){

            return LoginSuccessDto.builder()
                    .username(userEntity.getUsername())
                    .token(tokenProvider.create(userEntity))
                    .build();
        }

        return null;
    }




}
