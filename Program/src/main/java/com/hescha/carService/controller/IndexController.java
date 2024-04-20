package com.hescha.carService.controller;

import com.hescha.carService.service.ItemService;
import com.hescha.carService.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(path = {"/", "/index", "/home"})
public class IndexController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ItemService itemService;

    @GetMapping
    public String getIndex(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("entity",
                    userService.findByUsername(principal.getName()));
        }
        model.addAttribute("inventories",
                itemService.repository.findAll());
        return "index";
    }

}
