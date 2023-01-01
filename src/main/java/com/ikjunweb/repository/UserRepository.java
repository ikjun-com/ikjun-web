package com.ikjunweb.repository;

import com.ikjunweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
    int existsByUsername(String username);
    int existsByEmail(String email);
    int existsByNickname(String nickname);
}
