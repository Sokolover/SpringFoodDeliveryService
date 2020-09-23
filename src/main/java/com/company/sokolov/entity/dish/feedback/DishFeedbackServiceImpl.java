package com.company.sokolov.entity.dish.feedback;

import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DishFeedbackServiceImpl implements DishFeedbackService {

    @Autowired
    private DishFeedbackDao dishFeedbackDao;

    @Override
    public Optional<DishFeedback> findByDishIdAndUserAccountId(Long dishId, Long userId) {

        DishFeedback dishFeedback = dishFeedbackDao.findByDishIdAndUserId(dishId, userId);
        return Optional.ofNullable(dishFeedback);
    }

    @Override
    public void save(DishFeedback dishFeedback) {

        dishFeedbackDao.save(dishFeedback);
    }

    @Transactional
    @Override
    public void update(DishFeedback dishFeedback){

        dishFeedbackDao.update(
                dishFeedback.getId(),
                dishFeedback.getRating(),
                dishFeedback.getComment(),
                dishFeedback.getDish(),
                dishFeedback.getUser()
        );
    }
}
