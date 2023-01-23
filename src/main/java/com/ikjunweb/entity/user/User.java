package com.ikjunweb.entity.user;

import com.ikjunweb.entity.BaseEntity;
import com.ikjunweb.entity.board.BoardHate;
import com.ikjunweb.entity.board.BoardLike;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column
    private String provider;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column
    private Integer star;

    @OneToMany(mappedBy = "user")
    private List<BoardLike> likes;

    @OneToMany(mappedBy = "user")
    private List<BoardHate> hates;

    public void editUser(String nickname) {
        this.nickname = nickname;
    }

    public void addLike(BoardLike boardLike) {
        this.likes.add(boardLike);
    }

    public void removeLike(BoardLike boardLike) {
        likes.removeIf(item -> item.getId() == boardLike.getId());
    }

    public void addHate(BoardHate boardHate) {
        this.hates.add(boardHate);
    }

    public void removeHate(BoardHate boardHate) {
        likes.removeIf(item -> item.getId() == boardHate.getId());
    }

    @PrePersist
    public void perPersist() {
        this.star = this.star == null ? 0 : this.star;
    }
}
