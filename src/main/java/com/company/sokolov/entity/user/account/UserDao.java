package com.company.sokolov.entity.user.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByUsername(String username);
}
