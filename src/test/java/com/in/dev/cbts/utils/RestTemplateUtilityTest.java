package com.in.dev.cbts.utils;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class RestTemplateUtilityTest {

    @Test
    void getRestTemplate_initializes() throws Exception {
        RestTemplateUtility u = new RestTemplateUtility();
        RestTemplate r = u.getRestTemplate();
        assertNotNull(r);
        // second call returns same instance
        RestTemplate r2 = u.getRestTemplate();
        assertSame(r, r2);
    }
}
