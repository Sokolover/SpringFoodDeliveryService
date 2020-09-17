package com.company.sokolov.entity.user.account;

import com.company.sokolov.entity.user.address.UserAddressDao;
import com.company.sokolov.entity.user.role.UserRole;
import com.company.sokolov.entity.wallet.WalletDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static java.util.Objects.isNull;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private WalletDao walletDao;
    @Autowired
    private UserAddressDao userAddressDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userDao.findByUsername(username);

        if (isNull(user)) {
            throw new UsernameNotFoundException("User not found");
        }
        /*
        обьекты в моём классе User надо делать все Serializable чтобы работала Spring Security
        */
        return user;
    }

    @Override
    public boolean registerUser(User user) {

        user.setActive(true);
        user.setRoles(Collections.singleton(UserRole.USER));

        walletDao.save(user.getWallet());
        userAddressDao.save(user.getUserAddress());
        userDao.save(user);

        return true;
    }

    @Override
    public User findByEmail(String email) {

        return userDao.findByEmail(email);
    }

}
