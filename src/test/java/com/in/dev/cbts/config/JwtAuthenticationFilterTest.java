package com.in.dev.cbts.config;

import com.in.dev.cbts.service.JwtService;
import com.in.dev.cbts.service.MyUserDetailsService;
import com.in.dev.cbts.repository.UserRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.mockito.Mockito.*;

class JwtAuthenticationFilterTest {

    private JwtAuthenticationFilter filter;

    @Mock
    private JwtService jwtService;
    @Mock
    private MyUserDetailsService myUserDetailsService;
    @Mock
    private UserRepo userRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        filter = new JwtAuthenticationFilter();
        org.springframework.test.util.ReflectionTestUtils.setField(filter, "jwtService", jwtService);
        org.springframework.test.util.ReflectionTestUtils.setField(filter, "myUserDetailsService", myUserDetailsService);
        org.springframework.test.util.ReflectionTestUtils.setField(filter, "userRepo", userRepo);
    }

    @Test
    void doFilterInternal_noAuthHeader_passesThrough() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse resp = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();
        filter.doFilterInternal(req, resp, chain);
        // nothing set in security context
    }

    @Test
    void doFilterInternal_withInvalidToken_passesThrough() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.addHeader("Authorization","Bearer invalid");
        when(jwtService.extractUsername("invalid")).thenReturn("u");
        when(userRepo.findByUsername("u")).thenReturn(null);
        MockHttpServletResponse resp = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();
        filter.doFilterInternal(req, resp, chain);
    }

}
