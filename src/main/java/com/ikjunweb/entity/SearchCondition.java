package com.ikjunweb.entity;

import com.ikjunweb.entity.type.MajorType;
import com.ikjunweb.entity.type.SubjectType;
import lombok.Data;

@Data
public class SearchCondition {
    private String content;
    private MajorType majorType;
    private SubjectType subjectType;

    public SearchCondition(String content, MajorType majorType, SubjectType subjectType) {
        this.content = content;
        this.majorType = majorType;
        this.subjectType = subjectType;
    }
}
