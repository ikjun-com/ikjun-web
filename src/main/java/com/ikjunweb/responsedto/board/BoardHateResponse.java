package com.ikjunweb.responsedto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardHateResponse {
    private String nickname;
    private String title;
    private Boolean cancel;
    private HttpStatus httpStatus;
}
