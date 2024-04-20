package com.hescha.carService.repository;

import com.hescha.carService.entity.Item;
import com.hescha.carService.entity.ItemType;
import com.hescha.carService.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
