package com.surabhi.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderDetailId implements Serializable {

    private Long orderId;
    private Long itemId;

    // Default constructor
    public OrderDetailId() {}

    // Parameterized constructor
    public OrderDetailId(Long orderId, Long itemId) {
        this.orderId = orderId;
        this.itemId = itemId;
    }

    // Getters and Setters

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    // Override equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetailId)) return false;
        OrderDetailId that = (OrderDetailId) o;
        return Objects.equals(getOrderId(), that.getOrderId()) &&
               Objects.equals(getItemId(), that.getItemId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getItemId());
    }
}
