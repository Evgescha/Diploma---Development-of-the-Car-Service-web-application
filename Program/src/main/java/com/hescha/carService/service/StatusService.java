package com.hescha.carService.service;

import com.hescha.carService.entity.Status;
import com.hescha.carService.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService extends CrudImpl<Status> {

    public StatusRepository repository;

    @Autowired
    public StatusService(StatusRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
