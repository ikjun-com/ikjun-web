package com.ikjunweb.requestdto.board;

import com.ikjunweb.entity.type.MajorType;
import com.ikjunweb.entity.type.SubjectType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardWriteRequest {
    private String title;
    private String content;
    private String answer;
    private String explanation;
    private MajorType majorType;
    private SubjectType subjectType;
    private Integer unlockStar;
    private String username;
    private String email;
}
