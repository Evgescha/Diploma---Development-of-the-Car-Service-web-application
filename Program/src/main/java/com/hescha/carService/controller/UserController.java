package com.hescha.carService.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import com.hescha.carService.entity.Order;
import com.hescha.carService.entity.User;
import com.hescha.carService.service.OrderService;
import com.hescha.carService.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl service;
    @Autowired
    private OrderService serviceOrder;


    @GetMapping
    String get(Model model) {
        List<User> list = service.repository.findAll();
        model.addAttribute("list", list);
        return "user-list";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(@PathVariable("id") Long id) throws Exception {
        User read = service.read(id);
        for(Order order:read.getMyOrders()){

            serviceOrder.delete(order.getId());
        }
        service.delete(id);
        return "redirect:/user";
    }

    @RequestMapping(path = "/history")
    public String history(Model model, Principal principal) {
        User user = service.findByUsername(principal.getName());
        List<Order> myOrders = user.getMyOrders();
        Collections.reverse(myOrders);
        model.addAttribute("list", myOrders);
        model.addAttribute("user", user);
        return "history";
    }
}
