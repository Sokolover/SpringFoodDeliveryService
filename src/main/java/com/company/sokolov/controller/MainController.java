package com.company.sokolov.controller;

import com.company.sokolov.entity.user.account.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main() {
        return "home";
    }

    @GetMapping("/hello")
    public String hello(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        model.addAttribute("username", user.getUsername());
        return "hello";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

}
