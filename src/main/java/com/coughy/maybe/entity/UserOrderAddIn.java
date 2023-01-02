package com.coughy.maybe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserOrderAddIn implements Serializable {
    @EmbeddedId
    @JsonIgnore
    UserOrderAddInKey id;

    @ManyToOne
    @MapsId("userOrderId")
    @JoinColumn(name = "user_order_id")
    @JsonIgnore
    private UserOrder userOrder;

    @ManyToOne
    @MapsId("addInId")
    @JoinColumn(name = "add_in_id")
    private AddIn addIn;

    @Column(name = "add_in_quantity")
    Long addInQuantity;

    public UserOrderAddIn(UserOrder userOrder, AddIn addIn) {
        this.userOrder = userOrder;
        this.addIn = addIn;
        this.id = new UserOrderAddInKey(userOrder.getId(), addIn.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(userOrder, addIn);
    }
}
