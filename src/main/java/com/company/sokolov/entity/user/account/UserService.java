package com.company.sokolov.entity.user.account;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {

    void registerUser(User user);

    User findByEmail(String email);

    User findByUsername(String name);
}
