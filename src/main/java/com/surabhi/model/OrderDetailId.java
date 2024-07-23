package com.surabhi.model;

import java.io.Serializable;

import jakarta.persistence.*;

@Embeddable
public class OrderDetailId implements Serializable {
    
    @Column(name = "order_id")
    private Long orderId;
    
    @Column(name = "item_id")
    private Long itemId;

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
    
    // Getters and setters
    
    // Constructors
    
    // equals, hashCode methods (based on orderId and itemId)
}
