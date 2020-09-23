package com.company.sokolov.entity.dish.feedback;

import com.company.sokolov.entity.dish.Dish;
import com.company.sokolov.entity.user.account.User;

import javax.persistence.*;

@Entity
@Table(name = "dish_feedback")
public class DishFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer rating;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;
    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
