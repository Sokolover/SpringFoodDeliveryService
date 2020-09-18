package com.company.sokolov.entity.dish;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DishService {

    List<Dish> findAll();

    void save(Dish dish);
}
