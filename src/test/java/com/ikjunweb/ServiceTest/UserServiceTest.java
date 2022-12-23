package com.ikjunweb.ServiceTest;

import com.ikjunweb.entity.User;
import com.ikjunweb.repository.UserRepository;
import com.ikjunweb.requestdto.UserRegisterRequest;
import com.ikjunweb.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void saveUsers() {
        User user1 = User.builder()
                .nickname("user1")
                .username("user1")
                .password("password1")
                .build();
        userRepository.save(user1);
        User user2 = User.builder()
                .nickname("user2")
                .username("user2")
                .password("password2")
                .build();
        userRepository.save(user2);
    }

    @Test
    void loginTest() {
        User user1 = userRepository.findByUsernameAndPassword("user1", "password1");
        User user2 = userRepository.findByUsernameAndPassword("afsdaf", "password2");
        assertThat(user1).isNotNull();
        assertThat(user2).isEqualTo(null);
    }

    @Test
    void validateTest() {
        UserRegisterRequest userRegisterRequest1 = UserRegisterRequest.builder()
                .username("")
                .password("")
                .build();
        boolean test1 = validateUser(userRegisterRequest1);
        assertThat(test1).isEqualTo(false);

        UserRegisterRequest userRegisterRequest2 = UserRegisterRequest.builder()
                .username("a")
                .password("abc123")
                .build();
        boolean test2 = validateUser(userRegisterRequest2);
        assertThat(test2).isEqualTo(false);

        UserRegisterRequest userRegisterRequest3 = UserRegisterRequest.builder()
                .username("addd1")
                .password("abc123")
                .build();
        boolean test3 = validateUser(userRegisterRequest3);
        assertThat(test3).isEqualTo(true);
    }

    public boolean validateUser(UserRegisterRequest userRegisterRequest) {
        String username = userRegisterRequest.getUsername();
        String password = userRegisterRequest.getPassword();
        if(username.length() < 5 || password.length() < 5) {
            return false;
        }
        if(isNullAndEmpty(username) || !isCombination(username)) {
            return false;
        }
        if(isNullAndEmpty(password) || !isCombination(password)) {
            return false;
        }
        return true;
    }

    public boolean isNullAndEmpty(String str) {
        if(str == " " || str == "" || str == null) {
            return true;
        }
        return false;
    }

    public boolean isCombination(String str) {
        int intCount = 0;
        int charCount = 0;
        for(int i = 0; i < str.length(); i++) {
            if((str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') || (str.charAt(i) >= 'a' && str.charAt(i) <= 'z')) {
                charCount++;
            }
            else if(str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                intCount++;
            }
        }
        if(charCount == 0 || intCount == 0) return false;
        return true;
    }
}
