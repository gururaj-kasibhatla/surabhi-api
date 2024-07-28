package com.surabhi.dto;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private Long orderId;
    private Long userId;
    private String username;
    private Date orderDate;
    private Double totalAmount;
    private List<OrderSummaryDTO> orderDetails;

    // Getters and setters

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderSummaryDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderSummaryDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
