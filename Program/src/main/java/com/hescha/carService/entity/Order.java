package com.hescha.carService.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "myOrders")
@Data
public class Order extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToMany
    @JoinTable(
            name = "order_item",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    private Date dates;

    private Time times;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Override
    public String toString() {
        return "Order{" +
                "creator=" + creator.getFio() +
                ", dates=" + dates +
                ", times=" + times +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (creator != null ? !creator.equals(order.creator) :
                order.creator != null)
            return false;
        if (items != null ? !items.equals(order.items) : order.items != null)
            return false;
        if (dates != null ? !dates.equals(order.dates) : order.dates != null)
            return false;
        if (times != null ? !times.equals(order.times) : order.times != null)
            return false;
        return status != null ? status.equals(order.status) : order.status == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        result = 31 * result + (dates != null ? dates.hashCode() : 0);
        result = 31 * result + (times != null ? times.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
