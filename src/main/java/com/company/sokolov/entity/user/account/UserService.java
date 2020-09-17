package com.company.sokolov.entity.user.account;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {

    boolean registerUser(User user);

    User findByEmail(String email);
}
