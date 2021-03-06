package com.company.sokolov.entity.dish;

import com.company.sokolov.entity.dish.category.DishCategory;
import com.company.sokolov.entity.dish.feedback.DishFeedback;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private BigDecimal cost;
    private String description;
    private String picture;
    @ManyToOne
    @JoinColumn(name = "dish_category_id")
    private DishCategory category;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish")
    private Set<DishFeedback> dishFeedbacks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public DishCategory getCategory() {
        return category;
    }

    public void setCategory(DishCategory category) {
        this.category = category;
    }

    public Set<DishFeedback> getDishFeedbacks() {
        return dishFeedbacks;
    }

    public void setDishFeedbacks(Set<DishFeedback> dishFeedbacks) {
        this.dishFeedbacks = dishFeedbacks;
    }
}
