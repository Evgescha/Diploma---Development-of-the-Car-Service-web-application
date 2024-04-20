package com.hescha.carService.controller;

import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.hescha.carService.entity.Order;
import com.hescha.carService.entity.Status;
import com.hescha.carService.entity.User;
import com.hescha.carService.service.ItemService;
import com.hescha.carService.service.OrderService;
import com.hescha.carService.service.StatusService;
import com.hescha.carService.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService service;

    @Autowired
    ItemService serviceItem;

    @Autowired
    UserServiceImpl serviceUser;

    @Autowired
    StatusService serviceStatus;

    @GetMapping
    String get(Model model) {
        List<Order> list = service.repository.findAll();
        model.addAttribute("list", list);
        return "order-list";
    }


    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/order";
    }

    @RequestMapping(path = "/cancelOrder/{id}")
    public String cancel(Model model, @PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/user/history";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(Principal principal) throws Exception {
        User creator = serviceUser.findByUsername(principal.getName());
        Date dates = new Date(System.currentTimeMillis());
        Time times = new Time(System.currentTimeMillis());
        Status inProgress = serviceStatus.read(0);
        Order order = new Order();
        order.setCreator(creator);
        order.setDates(dates);
        order.setTimes(times);
        order.setStatus(inProgress);
        service.create(order);
        return "redirect:/order";
    }

    @RequestMapping(path = "/bookNow", method = RequestMethod.POST)
    public String bookNowPOST(Order order,
                              @Param("itemId") Long itemId,
                              @Param("timeToo") String timeToo,
                              Model model, Principal principal) {

        return "redirect:/user/history";
    }
}
