package com.ikjunweb.controller.api;

import com.ikjunweb.config.auth.PrincipalDetails;
import com.ikjunweb.requestdto.user.UserDeleteRequest;
import com.ikjunweb.requestdto.user.UserRegisterRequest;
import com.ikjunweb.requestdto.user.UserUpdateRequest;
import com.ikjunweb.responsedto.user.UserDeleteResponse;
import com.ikjunweb.responsedto.user.UserRegisterResponse;
import com.ikjunweb.responsedto.user.UserUpdateResponse;
import com.ikjunweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserApiController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserApiController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/ikjun/user/register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        UserRegisterResponse userRegisterResponse = userService.register(userRegisterRequest);

        if(userRegisterResponse.getHttpStatus() != HttpStatus.OK) return new ResponseEntity<>(userRegisterResponse, userRegisterResponse.getHttpStatus());
        return new ResponseEntity<>(userRegisterResponse, HttpStatus.OK);
    }

    @PutMapping("/ikjun/user/myprofile")
    public ResponseEntity<UserUpdateResponse> update(Authentication authentication, @RequestBody UserUpdateRequest userUpdateRequest) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        UserUpdateResponse userUpdateResponse = userService.update(principalDetails.getId(), userUpdateRequest);

        if(userUpdateResponse.getHttpStatus() != HttpStatus.OK) return new ResponseEntity<>(userUpdateResponse, userUpdateResponse.getHttpStatus());
        return new ResponseEntity<>(userUpdateResponse, HttpStatus.OK);
    }

    @DeleteMapping("/ikjun/user/myprofile")
    public ResponseEntity<UserDeleteResponse> delete(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody UserDeleteRequest userDeleteRequest) {
        UserDeleteResponse userDeleteResponse = userService.delete(principalDetails.getId(), userDeleteRequest);

        if(userDeleteResponse.getHttpStatus() != HttpStatus.OK) return new ResponseEntity<>(userDeleteResponse, userDeleteResponse.getHttpStatus());
        return new ResponseEntity<>(userDeleteResponse, HttpStatus.OK);
    }
}
