package com.ikjunweb;

import com.ikjunweb.entity.user.UserRole;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class DummyTest {

    @Test
    void test() {
        assertThat(UserRole.USER.toString()).isEqualTo("USER");
    }
}
