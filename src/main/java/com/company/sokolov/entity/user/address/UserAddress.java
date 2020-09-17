package com.company.sokolov.entity.user.address;

import com.company.sokolov.entity.user.account.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_address")
public class UserAddress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullAddress;
    @OneToOne(mappedBy = "userAddress")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
