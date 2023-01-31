package com.ikjunweb.responsedto.comment;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class CommentWriteResponse {
    String nickname;
    String title;
    HttpStatus httpStatus;
}
