package com.ikjunweb.ServiceTest;

import com.ikjunweb.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class methodTest {

    @Test
    void nullMethodTest() {
        boolean test1 = isNull(null);
        boolean test2 = isNull(new User());

        assertThat(test1).isEqualTo(false);
        assertThat(test2).isEqualTo(true);
    }

    boolean isNull(Object o) {
        if(o == null) return false;
        return true;
    }
}
