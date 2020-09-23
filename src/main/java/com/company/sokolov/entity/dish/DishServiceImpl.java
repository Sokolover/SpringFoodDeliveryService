package com.company.sokolov.entity.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public void delete(Long id) {
        dishDao.deleteById(id);
    }

    @Override
    public Dish findById(Long id) {
        return dishDao.findById(id).orElse(new Dish());
    }

    /*
    todo TIP
     when you see javax.persistence.TransactionRequiredException: Executing an update/delete query
     it means you need to write package @Transactional-annotation
     from package org.springframework.transaction.annotation;
     @
     it often becomes when you call update() method from dao
     */
    @Transactional
    @Override
    public void update(Dish dish) {

        dishDao.update(
                dish.getId(),
                dish.getName(),
                dish.getCost(),
                dish.getDescription(),
                dish.getPicture(),
                dish.getCategory()
        );
    }
}
