package com.company.sokolov.controller;

import com.company.sokolov.entity.dish.Dish;
import com.company.sokolov.entity.dish.DishService;
import com.company.sokolov.entity.user.account.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.company.sokolov.constants.CommonAppConstants.HOME_VIEW;
import static java.util.Objects.nonNull;

@Controller
public class MainController {

    @Autowired
    private DishService dishService;

    @GetMapping("/")
    public String main(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        return home(user, model);
    }

    @GetMapping("/home")
    public String home(
            @AuthenticationPrincipal User user,
            Model model
    ) {

        List<Dish> dishes = dishService.findAll();
        model.addAttribute("dishes", dishes);
        if (nonNull(user)) {
            model.addAttribute("username", user.getUsername());
        }
        return HOME_VIEW;
    }

}
