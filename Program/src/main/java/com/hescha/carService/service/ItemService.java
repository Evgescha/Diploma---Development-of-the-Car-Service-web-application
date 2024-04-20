package com.hescha.carService.service;

import com.hescha.carService.entity.Item;
import com.hescha.carService.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends CrudImpl<Item> {

    public ItemRepository repository;

    @Autowired
    public ItemService(ItemRepository repository) {
        super(repository);
        this.repository = repository;
    }


}
