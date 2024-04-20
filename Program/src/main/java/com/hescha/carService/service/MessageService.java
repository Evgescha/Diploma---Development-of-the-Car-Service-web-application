package com.hescha.carService.service;

import com.hescha.carService.entity.Item;
import com.hescha.carService.entity.Message;
import com.hescha.carService.repository.ItemRepository;
import com.hescha.carService.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService extends CrudImpl<Message> {

    public MessageRepository repository;

    @Autowired
    public MessageService(MessageRepository repository) {
        super(repository);
        this.repository = repository;
    }


}
