package com.ikjunweb.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateResponse {
    private String nickname;
    private HttpStatus httpStatus;
}
