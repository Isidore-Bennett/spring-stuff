package com.coughy.maybe.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
@Table(name = "user_order_add_in")
public class UserOrderAddInKey implements Serializable {
    private String userOrderId;
    private String addInId;

    public UserOrderAddInKey(String userOrderId, String addInId) {
        this.userOrderId = userOrderId;
        this.addInId = addInId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOrderAddInKey that = (UserOrderAddInKey) o;
        return Objects.equals(userOrderId, that.userOrderId) && Objects.equals(addInId, that.addInId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userOrderId, addInId);
    }
}
