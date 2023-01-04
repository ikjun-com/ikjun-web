package com.ikjunweb.service;

import com.ikjunweb.entity.user.User;
import com.ikjunweb.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceTest {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @BeforeEach
    void 유저_등록() {
        User user = User.builder()
                .nickname("nickname")
                .username("username123")
                .password("password123")
                .email("email")
                .build();
        userRepository.save(user);
        System.out.println("userId=" + user.getId());
    }

    @Test
    void 등록된_유저_확인() {
        User user = userRepository.findByUsernameAndPassword("username123", "password123");
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user).isNotEqualTo(null);
        assertThat(user.getEmail()).isEqualTo("email");
    }

    @Test
    void 유저_id로_찾기() {
        User user = userRepository.findById(1L).orElseThrow(() -> {
            return new IllegalArgumentException("찾기 실패");
        });
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("username123");
    }


//    @Order(4)
//    @Test
//    void 유저_삭제() {
//        userRepository.deleteById(1L);
//    }
}
