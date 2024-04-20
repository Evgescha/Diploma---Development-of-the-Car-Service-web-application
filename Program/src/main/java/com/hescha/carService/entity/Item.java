package com.hescha.carService.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Item extends AbstractEntity {

    private String name;

    private float price;

    @ManyToOne
    @JoinColumn(name = "item_type_id")
    private ItemType type;

    @ManyToMany(mappedBy = "items")
    private List<Order> orders = new ArrayList<>();

    @Override
    public String toString() {
        return name + ", " + price + ", " + type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Item item = (Item) o;

        if (Float.compare(item.price, price) != 0) return false;
        if (name != null ? !name.equals(item.name) : item.name != null)
            return false;
        if (type != null ? !type.equals(item.type) : item.type != null)
            return false;
        return orders != null ? orders.equals(item.orders) : item.orders == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price)
                : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        return result;
    }
}
