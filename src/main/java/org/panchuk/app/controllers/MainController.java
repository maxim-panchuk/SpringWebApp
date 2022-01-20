package org.panchuk.app.controllers;

import org.panchuk.app.entity.Dot;
import org.panchuk.app.service.DotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class MainController {

    private final DotService dotService;

    @Autowired
    public MainController (DotService dotService) {
        this.dotService = dotService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        ArrayList<Dot> dots = (ArrayList<Dot>) dotService.getAll();
        model.addAttribute("dots", dots);
        return "home";
    }
}
