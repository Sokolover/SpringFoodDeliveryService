package com.company.sokolov.controller;

import com.company.sokolov.entity.dish.Dish;
import com.company.sokolov.entity.dish.DishService;
import com.company.sokolov.entity.dish.category.DishCategory;
import com.company.sokolov.entity.dish.category.DishCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import static com.company.sokolov.constants.CommonAppConstants.*;

@Controller
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private DishCategoryService dishCategoryService;

    @GetMapping
    public String showDishes() {

        return HOME_VIEW;
    }

    @PostMapping("/add_order_item")
    public String addOrderItem() {


        return HOME_VIEW;
    }

    @GetMapping("/write_feedback")
    public String writeFeedback() {

        return "feedback_form";
    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("categories", dishCategoryService.findAll());

        return "add_dish";
    }

    @PostMapping("/add")
    public String add(
            Dish dish,
            @RequestParam String dishCategory,
            @RequestParam MultipartFile pictureFile,
            RedirectAttributes redirectAttributes
    ) {

        dish.setCategory(dishCategoryService.findByName(dishCategory));

        if (pictureFile.isEmpty()) {
            redirectAttributes.addFlashAttribute(MESSAGE_VIEW_ATTRIBUTE, "Please select a file to upload");
            return "redirect:/dish/add";
        }

        try {
            dish.setPicture(Base64.getEncoder().encodeToString(pictureFile.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        dishService.save(dish);

        redirectAttributes.addFlashAttribute(MESSAGE_VIEW_ATTRIBUTE, "Dish added to menu");

        return REDIRECT_HOME;
    }

    @PostMapping("/delete")
    public String delete() {


        return HOME_VIEW;
    }

    @PostMapping("/update")
    public String update() {

        return "update_form";
    }
}
