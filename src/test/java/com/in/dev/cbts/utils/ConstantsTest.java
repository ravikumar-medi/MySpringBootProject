package com.in.dev.cbts.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantsTest {

    @Test
    void constants_values_present() {
        assertTrue(Constants.BASE_PRODUCT_NAMES.contains("SIMODIS"));
        assertEquals(200, Constants.StatusCode.success);
        assertEquals("Success", Constants.Messages.success);
        assertEquals("INCIPIO", Constants.CashBackProducts.incipio);
    }
}
