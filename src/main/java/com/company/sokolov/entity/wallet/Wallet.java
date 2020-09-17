package com.company.sokolov.entity.wallet;

import com.company.sokolov.entity.user.account.User;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "wallet")
public class Wallet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal moneyAmount;
    @OneToOne(mappedBy = "wallet")
    private User user;

    public Wallet() {
        this.moneyAmount = new BigDecimal(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
