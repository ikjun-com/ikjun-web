package com.ikjunweb.requestdto.board;

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
    private String major;
    private String subject;
    private Integer unlockStar;
    private String username;
    private String email;
}
