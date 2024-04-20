package com.hescha.carService.service;

import com.hescha.carService.entity.Order;
import com.hescha.carService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends CrudImpl<Order> {

    public OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
