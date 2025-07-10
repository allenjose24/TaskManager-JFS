package com.example.TaskManagement.service;

import com.example.TaskManagement.model.UserPrincipals;
import com.example.TaskManagement.model.Users;
import com.example.TaskManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repository.findByUserName(username);

        if(user==null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("404 user not found");
        }

        return new UserPrincipals(user);
    }
}
