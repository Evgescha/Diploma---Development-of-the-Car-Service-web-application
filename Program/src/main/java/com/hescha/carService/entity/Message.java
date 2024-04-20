package com.hescha.carService.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class Message extends AbstractEntity  {
    private String name;
    private String email;
    private String phone;

    @Column(length = 10000, columnDefinition = "TEXT")
    private String message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Message message1 = (Message) o;

        if (name != null ? !name.equals(message1.name) : message1.name != null)
            return false;
        if (email != null ? !email.equals(message1.email) :
                message1.email != null)
            return false;
        if (phone != null ? !phone.equals(message1.phone) :
                message1.phone != null)
            return false;
        return message != null ? message.equals(message1.message) :
                message1.message == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
