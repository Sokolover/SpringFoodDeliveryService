package com.company.sokolov.entity.dish.feedback;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface DishFeedbackService {

    Optional<DishFeedback> findByDishIdAndUserAccountId(Long dishId, Long userId);

    void save(DishFeedback dishFeedback);

    void update(DishFeedback dishFeedback);
}
