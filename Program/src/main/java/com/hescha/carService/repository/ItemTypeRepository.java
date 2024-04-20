package com.hescha.carService.repository;

import com.hescha.carService.entity.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemTypeRepository extends JpaRepository<ItemType,
        Long> {
    ItemType findByNameIgnoreCase(String name);
}
