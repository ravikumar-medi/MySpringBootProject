package com.in.dev.cbts.service;

import com.in.dev.cbts.model.UserPrinciple;
import com.in.dev.cbts.repository.UserRepo;
import com.in.dev.cbts.model.Users;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@NoArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        if (user == null) {
            log.info("User Not Found");
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User Found");
        }
        return new UserPrinciple(user);
    }
}
