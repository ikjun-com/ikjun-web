package com.ikjunweb.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Board extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String answer;

    @Column
    private String explanation;

    @Column
    private String major;

    @Column
    private String subject;

    @Column
    private Integer viewCount;

    @Column
    private Integer unlockStar;

    @OneToMany(mappedBy = "board")
    private List<BoardTag> tags;

    @OneToMany(mappedBy = "board")
    private List<BoardLike> likes;

    @Column
    private Integer likeCount;

    @Column
    private Integer hateCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments;

    public void editBoard(String title, String content, String answer, String explanation, String major, String subject) {
        this.title = title;
        this.content = content;
        this.answer = answer;
        this.explanation = explanation;
        this.major = major;
        this.subject = subject;
    }

    public void increaseLikeCount() {
        this.likeCount = likeCount + 1;
    }

    public void decreaseLikeCount() {
        this.likeCount = likeCount - 1;
    }

    public void increaseHateCount() {
        this.hateCount = hateCount + 1;
    }

    public void decreaseHateCount() {
        this.hateCount = hateCount - 1;
    }

    @PrePersist
    public void prePersist() {
        this.title = this.title == null ? "untitled" : this.title;
        this.viewCount = this.viewCount == null ? 0 : this.viewCount;
        this.likeCount = this.likeCount == null ? 0 : this.likeCount;
        this.hateCount = this.hateCount == null ? 0 : this.hateCount;
    }
}
