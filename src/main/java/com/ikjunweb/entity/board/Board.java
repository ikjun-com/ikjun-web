package com.ikjunweb.entity.board;

import com.ikjunweb.entity.BaseEntity;
import com.ikjunweb.entity.comment.Comment;
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

    @OneToMany(mappedBy = "board")
    private List<BoardHate> hates;

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

    public void addLike(BoardLike boardLike) {
        this.likes.add(boardLike);
    }

    public void addHate(BoardHate boardHate) {
        this.hates.add(boardHate);
    }

    public void removeLike(BoardLike boardLike) {
        likes.removeIf(item -> item.getId() == boardLike.getId());
    }

    public void removeHate(BoardHate boardHate) {
        hates.removeIf(item -> item.getId() == boardHate.getId());
    }

    public void increaseViewCount() {
        this.viewCount = viewCount + 1;
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
