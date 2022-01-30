package org.panchuk.app.controllers;

import org.panchuk.app.entity.User;
import org.panchuk.app.service.UserService;
import org.panchuk.app.service.impl.UserServiceImpl;
import org.panchuk.app.util.SignInHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/main")
public class MainController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public MainController (UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;

    }

   /* @GetMapping("/sign_up")
    public String signUp (@RequestParam(value = "name") String name,
                        @RequestParam(value = "password") String password,
                        @RequestParam(value = "mail") String mail) {
        User us = User.builder().mail(mail).name(name).pass(password).build();
        userServiceImpl.addUser(us);
        return "home";
    }*/

    public String signUp (@ModelAttribute("user") User user) {
        userServiceImpl.addUser(user);
        return "home";
    }

    @PostMapping("/sign_in")
    public String signIn (@RequestParam(value = "mail") String mail,
                          @RequestParam(value = "password") String password) {
        User user = User.builder().mail(mail).pass(password).build();
        SignInHandler signInHandler = new SignInHandler(user, userServiceImpl);
        if (signInHandler.signIn()) return "home";
        return "sign_in_failed";
    }
}
