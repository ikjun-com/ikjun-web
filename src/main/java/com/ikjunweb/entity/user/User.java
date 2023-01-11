package com.ikjunweb.entity.user;

import com.ikjunweb.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

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

    public void editUser(String nickname) {
        this.nickname = nickname;
    }

    @PrePersist
    public void perPersist() {
        this.star = this.star == null ? 0 : this.star;
    }
}
