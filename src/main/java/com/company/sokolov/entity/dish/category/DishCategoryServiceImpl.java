package com.company.sokolov.entity.dish.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishCategoryServiceImpl implements DishCategoryService {

    @Autowired
    private DishCategoryDao dishCategoryDao;

    @Override
    public List<DishCategory> findAll() {
        return dishCategoryDao.findAll();
    }

    @Override
    public DishCategory findByName(String name) {
        return dishCategoryDao.findByName(name);
    }
}
