package com.hescha.carService.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class ItemType extends AbstractEntity {
    private String name;
    @Column(length = 1000)
    private String image1;
    @Column(length = 1000)
    private String image2;

    @Column(length = 10000, columnDefinition = "TEXT")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
    private List<Item> items = new ArrayList<Item>();

    @Override
    public String toString() {
        return name;
    }


}
