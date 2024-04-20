package com.hescha.carService.controller;

import com.hescha.carService.entity.ItemType;
import com.hescha.carService.entity.Message;
import com.hescha.carService.entity.User;
import com.hescha.carService.service.ItemTypeService;
import com.hescha.carService.service.MessageService;
import com.hescha.carService.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MessageService service;

    @GetMapping
    public String get(Model model, Principal principal) {
        model.addAttribute("list", service.repository.findAll());

        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            model.addAttribute("entity", user);
        }

        return "contact";
    }

    @PostMapping
    public String post(Model model, Principal principal, @ModelAttribute Message message) {
        model.addAttribute("list", service.repository.findAll());

        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            model.addAttribute("entity", user);
        }
        createOrUpdate(message);
        model.addAttribute("message","Ваше сообщение отправлено!");
        return "contact";
    }


    @RequestMapping(path = "/delete/{id}")
    public String delete(@PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/contact";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(Message entity) {
        service.create(entity);
        return "redirect:/contact";
    }

}
