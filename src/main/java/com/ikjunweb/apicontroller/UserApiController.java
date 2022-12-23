package com.ikjunweb.apicontroller;

import com.ikjunweb.requestdto.UserLoginRequest;
import com.ikjunweb.requestdto.UserRegisterRequest;
import com.ikjunweb.requestdto.UserUpdateRequest;
import com.ikjunweb.responsedto.UserLoginResponse;
import com.ikjunweb.responsedto.UserRegisterResponse;
import com.ikjunweb.responsedto.UserUpdateResponse;
import com.ikjunweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserApiController {

    private final UserService userService;

    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/ikjun/user/register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        UserRegisterResponse userRegisterResponse = userService.register(userRegisterRequest);

        if(userRegisterResponse.getHttpStatus() == HttpStatus.BAD_REQUEST) return new ResponseEntity<>(userRegisterResponse, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userRegisterResponse, HttpStatus.OK);
    }

    @PostMapping("/ikjun/user/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        UserLoginResponse userLoginResponse = userService.login(userLoginRequest);

        if(userLoginResponse.getHttpStatus() == HttpStatus.BAD_REQUEST) return new ResponseEntity<>(userLoginResponse, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userLoginResponse, HttpStatus.OK);
    }

    @PutMapping("/ikjun/user/{id}")
    public ResponseEntity<UserUpdateResponse> update(@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest) {
        UserUpdateResponse userUpdateResponse = userService.update(id, userUpdateRequest);

        if(userUpdateResponse.getHttpStatus() == HttpStatus.BAD_REQUEST) return new ResponseEntity<>(userUpdateResponse, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userUpdateResponse, HttpStatus.OK);
    }
}
