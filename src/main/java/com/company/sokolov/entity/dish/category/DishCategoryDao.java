package com.company.sokolov.entity.dish.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DishCategoryDao extends JpaRepository<DishCategory, Long> {

    DishCategory findByName(String name);
}
