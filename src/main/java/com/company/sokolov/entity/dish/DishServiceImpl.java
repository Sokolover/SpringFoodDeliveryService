package com.company.sokolov.entity.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishDao dishDao;

    @Override
    public List<Dish> findAll() {
        return dishDao.findAll();
    }

    @Override
    public void save(Dish dish) {
        dishDao.save(dish);
    }
}
