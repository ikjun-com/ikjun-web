package com.ikjunweb.entity.board;

import com.ikjunweb.entity.BaseEntity;
import com.ikjunweb.entity.comment.Comment;
import com.ikjunweb.entity.type.MajorType;
import com.ikjunweb.entity.type.SubjectType;
import com.ikjunweb.entity.user.User;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Board extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(length = 10000)
    private String content;

    @Column
    private String answer;

    @Column
    private String explanation;

    @Enumerated(EnumType.STRING)
    private MajorType majorType;

    @Enumerated(EnumType.STRING)
    private SubjectType subjectType;

    @Column
    private Integer viewCount;

    @Column
    private Integer unlockStar;

    @OneToMany(mappedBy = "board")
    private List<BoardTag> tags;

    @OneToMany(mappedBy = "board")
    private List<BoardLike> likes;

    @OneToMany(mappedBy = "board")
    private List<BoardHate> hates;

    @Column
    private Long likeCount;

    @Column
    private Long hateCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments;

    public void editBoard(String title, String content, String answer, String explanation, MajorType majorType, SubjectType subjectType) {
        this.title = title;
        this.content = content;
        this.answer = answer;
        this.explanation = explanation;
        this.majorType = majorType;
        this.subjectType = subjectType;
    }

    public void increaseViewCount() {
        this.viewCount = viewCount + 1;
    }

    public void setLikeCount(Long count) {
        this.likeCount = count;
    }

    @PrePersist
    public void prePersist() {
        this.title = this.title == null ? "untitled" : this.title;
        this.viewCount = this.viewCount == null ? 0 : this.viewCount;
        this.likeCount = this.likeCount == null ? 0 : this.likeCount;
        this.hateCount = this.hateCount == null ? 0 : this.hateCount;
    }
}
