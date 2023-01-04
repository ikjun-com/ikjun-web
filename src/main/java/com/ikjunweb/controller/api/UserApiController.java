package com.ikjunweb.controller.api;

import com.ikjunweb.requestdto.user.UserDeleteRequest;
import com.ikjunweb.requestdto.user.UserLoginRequest;
import com.ikjunweb.requestdto.user.UserRegisterRequest;
import com.ikjunweb.requestdto.user.UserUpdateRequest;
import com.ikjunweb.responsedto.user.UserDeleteResponse;
import com.ikjunweb.responsedto.user.UserLoginResponse;
import com.ikjunweb.responsedto.user.UserRegisterResponse;
import com.ikjunweb.responsedto.user.UserUpdateResponse;
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

        if(userRegisterResponse.getHttpStatus() != HttpStatus.OK) return new ResponseEntity<>(userRegisterResponse, userRegisterResponse.getHttpStatus());
        return new ResponseEntity<>(userRegisterResponse, HttpStatus.OK);
    }

    @PutMapping("/ikjun/user/{id}")
    public ResponseEntity<UserUpdateResponse> update(@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest) {
        UserUpdateResponse userUpdateResponse = userService.update(id, userUpdateRequest);

        if(userUpdateResponse.getHttpStatus() != HttpStatus.OK) return new ResponseEntity<>(userUpdateResponse, userUpdateResponse.getHttpStatus());
        return new ResponseEntity<>(userUpdateResponse, HttpStatus.OK);
    }

    @DeleteMapping("/ikjun/user/{id}")
    public ResponseEntity<UserDeleteResponse> delete(@PathVariable Long id, @RequestBody UserDeleteRequest userDeleteRequest) {
        UserDeleteResponse userDeleteResponse = userService.delete(id, userDeleteRequest);

        if(userDeleteResponse.getHttpStatus() != HttpStatus.OK) return new ResponseEntity<>(userDeleteResponse, userDeleteResponse.getHttpStatus());
        return new ResponseEntity<>(userDeleteResponse, HttpStatus.OK);
    }
}
