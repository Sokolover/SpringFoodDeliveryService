package com.company.sokolov.entity.dish.feedback;

import com.company.sokolov.entity.dish.Dish;
import com.company.sokolov.entity.dish.category.DishCategory;
import com.company.sokolov.entity.user.account.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface DishFeedbackDao extends JpaRepository<DishFeedback, Long> {

    DishFeedback findByDishIdAndUserId(Long dishId, Long userId);

    @Modifying(clearAutomatically = true)
    @Query("update DishFeedback dishFeedback set " +
            "dishFeedback.rating = :rating, " +
            "dishFeedback.comment = :comment, " +
            "dishFeedback.dish = :dish, " +
            "dishFeedback.user = :user " +
            "where dishFeedback.id = :id")
    void update(@Param("id") Long id,
                @Param("rating") Integer rating,
                @Param("comment") String comment,
                @Param("dish") Dish dish,
                @Param("user") User user);
}
