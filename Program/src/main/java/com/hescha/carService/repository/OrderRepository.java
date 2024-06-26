package com.hescha.carService.repository;

import com.hescha.carService.entity.Order;
import com.hescha.carService.entity.Status;
import com.hescha.carService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCreator(User creator);

    List<Order> findByStatus(Status status);

    Order findByCreatorAndDates(User creator, Date dates);

    Order findByCreatorAndStatus(User creator, Status status);

    Order findByCreatorAndStatusAndDates(User creator, Status status, Date dates);
}
