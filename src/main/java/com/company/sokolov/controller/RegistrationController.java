package com.company.sokolov.controller;

import com.company.sokolov.entity.user.account.User;
import com.company.sokolov.entity.user.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.company.sokolov.constants.CommonAppConstants.*;
import static java.util.Objects.nonNull;
import static org.springframework.util.StringUtils.isEmpty;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return REGISTRATION_VIEW;
    }

    @PostMapping("/registration")
    public String addUserAccount(@RequestParam String passwordConfirm,
                                 User user,
                                 Model model) {

        User userFromDatabase = userService.findByEmail(user.getEmail());
        List<String> errors = new ArrayList<>();

        if (nonNull(userFromDatabase)) {
            model.addAttribute(ERRORS_VIEW_ATTRIBUTE, Collections.singletonList("User already registered"));
            return REGISTRATION_VIEW;
        }

        if (isEmpty(passwordConfirm)) {
            errors.add("Password confirmation cannot be empty");
        }

        if (nonNull(user.getPassword()) && !user.getPassword().equals(passwordConfirm)) {
            errors.add("Passwords are different!");
        }

        if (!errors.isEmpty()) {
            model.addAttribute(ERRORS_VIEW_ATTRIBUTE, errors);
            return REGISTRATION_VIEW;
        }

        userService.registerUser(user);

        return REDIRECT_LOGIN;
    }
}
