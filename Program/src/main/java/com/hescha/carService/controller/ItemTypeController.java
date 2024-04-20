package com.hescha.carService.controller;

import java.util.List;

import com.hescha.carService.entity.ItemType;
import com.hescha.carService.service.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/services")
public class ItemTypeController {

    @Autowired
    private ItemTypeService service;

    @GetMapping
    public String getCategory(Model model) {
        model.addAttribute("list", service.repository.findAll());
        return "services";
    }

    @RequestMapping("/items/{id}")
    public String items(Model model, @PathVariable("id") Long id) {
        model.addAttribute("entity",service.read(id));
        model.addAttribute("list", service.repository.findAll());
        return "service-one";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id) {
        if (id != null) {
            ItemType entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new ItemType());
        }
        return "type-add-edit";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/services";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(ItemType entity) {
        service.create(entity);
        return "redirect:/services";
    }
}
