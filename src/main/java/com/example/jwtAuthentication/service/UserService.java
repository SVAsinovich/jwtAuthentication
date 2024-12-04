package com.example.jwtAuthentication.service;

import com.example.jwtAuthentication.domain.model.Role;
import com.example.jwtAuthentication.domain.model.User;
import com.example.jwtAuthentication.exception.EntityAlreadyExistsException;
import com.example.jwtAuthentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new EntityAlreadyExistsException("User with username already exists");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new EntityAlreadyExistsException("User with email already exists");
        }

        return save(user);
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username not found"));

    }

    //For Spring Security
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    //Get current user from context
    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }
}
