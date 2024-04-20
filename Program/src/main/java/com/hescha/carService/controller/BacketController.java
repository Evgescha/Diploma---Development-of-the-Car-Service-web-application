package com.hescha.carService.controller;

import com.hescha.carService.entity.Item;
import com.hescha.carService.entity.Order;
import com.hescha.carService.entity.User;
import com.hescha.carService.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Controller
@RequestMapping("/backet")
public class BacketController {

    @Autowired
    private ItemTypeService serviceItemType;

    @Autowired
    private ItemService serviceItem;

    @Autowired
    private UserServiceImpl serviceUser;

    @Autowired
    private OrderService serviceOrder;

    @Autowired
    private StatusService serviceStatus;


    @RequestMapping("/addToBacket/{id}")
    String getBooking(Model model, @PathVariable("id") Long id,
                      Principal principal) {
        User user = serviceUser.findByUsername(principal.getName());
        Item item = serviceItem.read(id);
        addOrderItemToBacket(user, item);
        return "redirect:/user/history";
    }

    @RequestMapping("/removeFromBacket/{id}")
    String removeFromBacket(@PathVariable("id") Long id,
                            Principal principal) {
        User user = serviceUser.findByUsername(principal.getName());
        List<Order> userOrders = user.getMyOrders();
        for (Order order : userOrders) {
            if (order.getStatus().getId() == 1) {
                List<Item> items = order.getItems();
                for (Item orderItem : items) {
                    if (orderItem.getId() == id) {
                        items.remove(orderItem);
                        serviceOrder.update(order);
                        serviceUser.update(user);
                        break;
                    }
                }
            }
        }
        return "redirect:/user/history";
    }

    private void addOrderItemToBacket(User user, Item item) {
        Order order = null;
        for (Order o : user.getMyOrders()) {
            if (o.getStatus().getId() == 1) {
                order = o;
                break;
            }
        }
        if (order == null) {
            order = new Order();
            order.setStatus(serviceStatus.read(1));
            order.setCreator(user);
            order.setDates(new Date(System.currentTimeMillis()));
            order.setTimes(new Time(System.currentTimeMillis()));
            serviceOrder.create(order);
            user.getMyOrders().add(order);
            serviceUser.update(user);
            serviceUser.update(user);
            addOrderItemToBacket(serviceUser.read(user.getId()), item);
        } else {
            order.getItems().add(item);
            item.getOrders().add(order);
            serviceOrder.update(order);
        }
    }

}
