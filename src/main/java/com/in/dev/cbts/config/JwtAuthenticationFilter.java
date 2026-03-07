package com.in.dev.cbts.config;

import com.in.dev.cbts.model.Users;
import com.in.dev.cbts.repository.UserRepo;
import com.in.dev.cbts.service.JwtService;
import com.in.dev.cbts.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    UserRepo userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        log.info("authHeader: " + authHeader);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.info("Invalid authHeader!");
            filterChain.doFilter(request, response);
            return;
        }
//        Utils.getBear
        final String jwt = authHeader.substring(7);
        log.info("jwt: " + jwt);
        final String username = jwtService.extractUsername(jwt);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Users user = userRepo.findByUsername(username);
            if (user != null && jwtService.isTokenValid(jwt, user)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(user, null, List.of());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
