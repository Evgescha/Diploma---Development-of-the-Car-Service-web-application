package com.hescha.carService.controller;


import com.hescha.carService.entity.Item;
import com.hescha.carService.service.ItemService;
import com.hescha.carService.service.ItemTypeService;
import com.hescha.carService.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService service;

    @Autowired
    ItemTypeService itemTypeService;


    @Autowired
    UserServiceImpl userService;


    @RequestMapping(path = "/delete/{id}")
    public String delete(@PathVariable("id") Long id) throws Exception {
        Long servId = service.read(id).getType().getId();
        service.delete(id);
        return "redirect:/services/" + servId;
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id) {


        if (id != null) {
            Item entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new Item());
        }
        model.addAttribute("types", itemTypeService.repository.findAll());
        return "item-add-edit";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(Item entity, @Param("catId") Long catId) {

        entity.setType(itemTypeService.read(catId));
        service.create(entity);
        return "redirect:/services/items/" + catId;
    }


}
