package com.hescha.carService.repository;

import com.hescha.carService.entity.Item;
import com.hescha.carService.entity.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	
	List<Item> findByNameIgnoreCase(String name);

	List<Item> findByPrice(float price);

	List<Item> findByType(ItemType typeFood);
}
