package com.company.sokolov.entity.dish.category;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DishCategoryService {

    List<DishCategory> findAll();

    DishCategory findByName(String name);
}
