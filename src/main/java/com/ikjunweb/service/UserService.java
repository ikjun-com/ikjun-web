package com.ikjunweb.service;

import com.ikjunweb.entity.User;
import com.ikjunweb.entity.UserRole;
import com.ikjunweb.repository.UserRepository;
import com.ikjunweb.requestdto.UserDeleteRequest;
import com.ikjunweb.requestdto.UserLoginRequest;
import com.ikjunweb.requestdto.UserRegisterRequest;
import com.ikjunweb.requestdto.UserUpdateRequest;
import com.ikjunweb.responsedto.UserDeleteResponse;
import com.ikjunweb.responsedto.UserLoginResponse;
import com.ikjunweb.responsedto.UserRegisterResponse;
import com.ikjunweb.responsedto.UserUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public UserDeleteResponse delete(Long id, UserDeleteRequest userDeleteRequest) {
        User user = userRepository.findByUsernameAndPassword(userDeleteRequest.getUsername(), userDeleteRequest.getPassword());
        if(user == null) {
            UserDeleteResponse userDeleteResponse = UserDeleteResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
            return userDeleteResponse;
        }
        userRepository.deleteById(id);
        UserDeleteResponse userDeleteResponse = UserDeleteResponse.builder()
                .nickname(user.getNickname())
                .httpStatus(HttpStatus.OK)
                .build();
        return userDeleteResponse;
    }

    @Transactional
    public UserUpdateResponse update(Long id, UserUpdateRequest userUpdateRequest) {
        if(!validateUserUpdate(userUpdateRequest)) {
            UserUpdateResponse userUpdateResponse = UserUpdateResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
            return userUpdateResponse;
        }
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정 실패");
        });

        user.editUser(userUpdateRequest.getNickname());

        UserUpdateResponse userUpdateResponse = UserUpdateResponse.builder()
                .nickname(user.getNickname())
                .httpStatus(HttpStatus.OK)
                .build();
        return userUpdateResponse;
    }

    @Transactional
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        User user = userRepository.findByUsernameAndPassword(userLoginRequest.getUsername(), userLoginRequest.getPassword());
        if(user == null) {
            UserLoginResponse userLoginResponse = UserLoginResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
            return userLoginResponse;
        }
        UserLoginResponse userLoginResponse = UserLoginResponse.builder()
                .nickname(user.getNickname())
                .httpStatus(HttpStatus.OK)
                .build();
        return userLoginResponse;
    }

    @Transactional
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        if(!validateUserRegister(userRegisterRequest)) {
            UserRegisterResponse userRegisterResponse = UserRegisterResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
            return userRegisterResponse;
        }

        String rawPassword = userRegisterRequest.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        User user = User.builder()
                .nickname(userRegisterRequest.getNickname())
                .username(userRegisterRequest.getUsername())
                .password(encPassword)
                .email(userRegisterRequest.getEmail())
                .provider("ikjun")
                .userRole(UserRole.USER)
                .build();
        userRepository.save(user);

        UserRegisterResponse userRegisterResponse = UserRegisterResponse.builder()
                .nickname(user.getNickname())
                .username(user.getUsername())
                .email(user.getEmail())
                .httpStatus(HttpStatus.OK)
                .build();
        return userRegisterResponse;
    }

    private boolean validateUserRegister(UserRegisterRequest userRegisterRequest) {
        String nickname = userRegisterRequest.getNickname();
        String username = userRegisterRequest.getUsername();
        String password = userRegisterRequest.getPassword();
        String email = userRegisterRequest.getEmail();
        if(username.length() < 5 || password.length() < 5) {
            return false;
        }
        if(isNullAndEmpty(nickname) || isNullAndEmpty(email) || isNullAndEmpty(username) || isNullAndEmpty(password)) return false;
        if(!isCombination(username) || !isCombination(password)) return false;
        return true;
    }

    private boolean validateUserUpdate(UserUpdateRequest userUpdateRequest) {
        String nickname = userUpdateRequest.getNickname();
        if(isNullAndEmpty(nickname)) return false;
        return true;
    }

    private boolean isNullAndEmpty(String str) {
        if(str == " " || str == "" || str == null) {
            return true;
        }
        return false;
    }

    private boolean isCombination(String str) {
        int intCount = 0;
        int charCount = 0;
        for(int i = 0; i < str.length(); i++) {
            if((str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') || (str.charAt(i) >= 'a' && str.charAt(i) <= 'z')) {
                charCount++;
            }
            else if(str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                intCount++;
            }
        }
        if(charCount == 0 || intCount == 0) return false;
        return true;
    }

    @Transactional
    public boolean isUsernameOverlap(String username) {
        boolean exist = userRepository.existsByUsername(username);
        return exist;
    }

    @Transactional
    public boolean isEmailOverLap(String email) {
        boolean exist = userRepository.existsByEmail(email);
        return exist;
    }

    @Transactional
    public boolean isNicknameOverLap(String nickname) {
        boolean exist = userRepository.existsByNickname(nickname);
        return exist;
    }
}