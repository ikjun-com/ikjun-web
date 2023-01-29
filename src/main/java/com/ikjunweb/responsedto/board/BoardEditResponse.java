package com.ikjunweb.responsedto.board;

import com.ikjunweb.entity.type.SubjectType;
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
    private SubjectType subjectType;
    private String nickname;
    private HttpStatus httpStatus;
}
