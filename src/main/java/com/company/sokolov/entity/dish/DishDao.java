package com.company.sokolov.entity.dish;

import com.company.sokolov.entity.dish.category.DishCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface DishDao extends JpaRepository<Dish, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Dish dish set " +
            "dish.name = :name, " +
            "dish.cost = :cost, " +
            "dish.description = :description, " +
            "dish.picture = :picture, " +
            "dish.category = :category " +
            "where dish.id = :id")
    void update(@Param("id") Long id,
                @Param("name") String name,
                @Param("cost") BigDecimal cost,
                @Param("description") String description,
                @Param("picture") String picture,
                @Param("category") DishCategory category);
}
