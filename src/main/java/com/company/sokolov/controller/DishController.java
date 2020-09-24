package com.company.sokolov.controller;

import com.company.sokolov.entity.dish.Dish;
import com.company.sokolov.entity.dish.DishService;
import com.company.sokolov.entity.dish.category.DishCategoryService;
import com.company.sokolov.entity.dish.feedback.DishFeedback;
import com.company.sokolov.entity.dish.feedback.DishFeedbackService;
import com.company.sokolov.entity.user.account.User;
import com.company.sokolov.entity.user.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.annotation.MultipartConfig;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import static com.company.sokolov.constants.CommonAppConstants.*;
import static java.util.Objects.isNull;

@Controller
@RequestMapping("/dish")
@MultipartConfig(maxFileSize = 5_242_880L, maxRequestSize = 5_242_880L, fileSizeThreshold = 5_242_880)
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private DishCategoryService dishCategoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private DishFeedbackService dishFeedbackService;

    @GetMapping
    public String showDishes() {

        return HOME_VIEW;
    }

    @PostMapping("/add_order_item")
    public String addOrderItem() {


        return HOME_VIEW;
    }

    @PostMapping("/write_feedback")
    public String writeFeedback(
            @AuthenticationPrincipal User user,
            @RequestParam Long dishId,
            DishFeedback dishFeedback,
            RedirectAttributes redirectAttributes
    ) {

        /*
        TODO try and test:
                  НЕ   String currentPrincipalName = SecurityContextHolder.getContext().getAuthentication().getName();
                        User user = userService.findByUsername(currentPrincipalName);
                  А
                         public String writeFeedback(
                        @AuthenticationPrincipal User user,
            https://urvanov.ru/2016/07/24/spring-mvc-%D0%BF%D0%BE%D0%BB%D1%83%D1%87%D0%B0%D0%B5%D0%BC-%D1%82%D0%B5%D0%BA%D1%83%D1%89%D0%B5%D0%B3%D0%BE-%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D1%82%D0%B5%D0%BB%D1%8F-%D0%B2-%D0%BA/
         */

//        String currentPrincipalName = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = userService.findByUsername(currentPrincipalName);

        Optional<DishFeedback> optionalDishFeedbackFromDatabase = dishFeedbackService.findByDishIdAndUserAccountId(dishId, user.getId());
        if (optionalDishFeedbackFromDatabase.isPresent()) {
            DishFeedback dishFeedbackFromDatabase = optionalDishFeedbackFromDatabase.get();
            dishFeedbackFromDatabase.setRating(dishFeedback.getRating());
            dishFeedbackFromDatabase.setComment(dishFeedback.getComment());
            dishFeedbackService.update(dishFeedback);
        } else {
            dishFeedback.setDish(dishService.findById(dishId));
            dishFeedback.setUser(user);
            dishFeedbackService.save(dishFeedback);
        }

        redirectAttributes.addFlashAttribute(MESSAGE_VIEW_ATTRIBUTE, "Feedback send");

        return REDIRECT_HOME;
    }

    @GetMapping("/write_feedback")
    public String writeFeedback(
            @RequestParam Long dishId,
            Model model
    ) {

        model.addAttribute("dish", dishService.findById(dishId));

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
    public String delete(
            @RequestParam Long dishId,
            RedirectAttributes redirectAttributes
    ) {

        dishService.delete(dishId);

        redirectAttributes.addFlashAttribute(MESSAGE_VIEW_ATTRIBUTE, "Dish deleted from menu");

        return REDIRECT_HOME;
    }

    @GetMapping("/update")
    public String update(
            @RequestParam Long dishId,
            Model model
    ) {

        model.addAttribute("dish", dishService.findById(dishId));
        model.addAttribute("categories", dishCategoryService.findAll());

        return "update_form";
    }

    @PostMapping("/update")
    public String update(
            Dish dish,
            @RequestParam String dishCategory,
            @RequestParam MultipartFile pictureFile,
            RedirectAttributes redirectAttributes
    ) {

        Dish dishFromDatabase = dishService.findById(dish.getId());

        dish.setCategory(dishCategoryService.findByName(dishCategory));

        if (dish.getName().isEmpty()) {
            dish.setName(dishFromDatabase.getName());
        }

        if (isNull(dish.getCost())) {
            dish.setCost(dishFromDatabase.getCost());
        }

        if (dish.getDescription().isEmpty()) {
            dish.setDescription(dishFromDatabase.getDescription());
        }

        if (pictureFile.isEmpty()) {
            dish.setPicture(dishFromDatabase.getPicture());
        } else {
            try {
                dish.setPicture(Base64.getEncoder().encodeToString(pictureFile.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        dishService.update(dish);

        redirectAttributes.addFlashAttribute(MESSAGE_VIEW_ATTRIBUTE, "Dish updated");

        return REDIRECT_HOME;
    }
}
