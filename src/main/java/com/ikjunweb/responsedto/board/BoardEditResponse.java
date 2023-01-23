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
public class BoardEditResponse {
    private String title;
    private String subject;
    private String nickname;
    private HttpStatus httpStatus;
}
