package com.ikjunweb.controller.api;

import com.ikjunweb.config.auth.PrincipalDetail;
import com.ikjunweb.requestdto.user.UserDeleteRequest;
import com.ikjunweb.requestdto.user.UserRegisterRequest;
import com.ikjunweb.requestdto.user.UserUpdateRequest;
import com.ikjunweb.responsedto.user.UserDeleteResponse;
import com.ikjunweb.responsedto.user.UserRegisterResponse;
import com.ikjunweb.responsedto.user.UserUpdateResponse;
import com.ikjunweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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

        return new ResponseEntity<>(userRegisterResponse, userRegisterResponse.getHttpStatus());
    }

    @PutMapping("/ikjun/user/myprofile")
    public ResponseEntity<UserUpdateResponse> update(Authentication authentication, @RequestBody UserUpdateRequest userUpdateRequest) {
        PrincipalDetail principalDetail = (PrincipalDetail) authentication.getPrincipal();
        UserUpdateResponse userUpdateResponse = userService.update(principalDetail.getId(), userUpdateRequest);

        return new ResponseEntity<>(userUpdateResponse, userUpdateResponse.getHttpStatus());
    }

    @DeleteMapping("/ikjun/user/myprofile")
    public ResponseEntity<UserDeleteResponse> delete(@AuthenticationPrincipal PrincipalDetail principalDetail, @RequestBody UserDeleteRequest userDeleteRequest) {
        UserDeleteResponse userDeleteResponse = userService.delete(principalDetail.getId(), userDeleteRequest);

        return new ResponseEntity<>(userDeleteResponse, userDeleteResponse.getHttpStatus());
    }
}
