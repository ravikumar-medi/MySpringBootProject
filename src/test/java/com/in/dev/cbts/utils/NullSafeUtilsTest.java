package com.in.dev.cbts.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class NullSafeUtilsTest {

    @Test
    void safeString_whenNull_returnsEmpty() {
        assertEquals("", NullSafeUtils.safeString(null));
    }

    @Test
    void safeString_whenNotNull_returnsSame() {
        assertEquals("abc", NullSafeUtils.safeString("abc"));
    }

    @Test
    void safeLong_tests() {
        assertEquals(0L, NullSafeUtils.safeLong(null));
        assertEquals(5L, NullSafeUtils.safeLong(5L));
    }

    @Test
    void safeInteger_tests() {
        assertEquals(0, NullSafeUtils.safeInteger(null));
        assertEquals(10, NullSafeUtils.safeInteger(10));
    }

    @Test
    void safeBigDecimal_tests() {
        assertEquals(BigDecimal.ZERO, NullSafeUtils.safeBigDecimal(null));
        assertEquals(new BigDecimal("1.23"), NullSafeUtils.safeBigDecimal(new BigDecimal("1.23")));
    }
}
