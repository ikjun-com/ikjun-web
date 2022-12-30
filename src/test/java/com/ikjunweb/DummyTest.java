package com.ikjunweb;

import com.ikjunweb.entity.UserRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class DummyTest {

    @Test
    void test() {
        assertThat(UserRole.USER.toString()).isEqualTo("USER");
    }
}
