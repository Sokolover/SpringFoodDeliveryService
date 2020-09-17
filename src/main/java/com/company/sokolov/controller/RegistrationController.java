package com.company.sokolov.controller;

import com.company.sokolov.entity.user.account.User;
import com.company.sokolov.entity.user.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.company.sokolov.constants.CommonAppConstants.ERRORS_JSP_ATTRIBUTE;
import static java.util.Objects.nonNull;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUserAccount(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String passwordConfirm,
                                 @RequestParam String email,
                                 @RequestParam String phoneNumber,
                                 @RequestParam String address,
                                 Model model) {

        User userFromDatabase = userService.findByEmail(email);

        if (nonNull(userFromDatabase)) {
            model.addAttribute(ERRORS_JSP_ATTRIBUTE, "User already registered");
            return "registration";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(passwordEncoder.encode(password));
        user.getUserAddress().setFullAddress(address);

        System.out.println(passwordConfirm);

        userService.registerUser(user);

        return "redirect:/login";
    }
}
