package com.surabhi.dto;

import java.util.Date;
import java.util.List;

public class OrderSummaryDTO {
    private Long orderId;
    private Long userId;
    private Double totalAmount;
    private Date orderDate;
    private List<MenuItemDTO> menuItems;

    // Constructor
    public OrderSummaryDTO(Long orderId, Long userId, Double totalAmount, Date orderDate, List<MenuItemDTO> menuItems) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.menuItems = menuItems;
    }

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

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<MenuItemDTO> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemDTO> menuItems) {
        this.menuItems = menuItems;
    }

    // Nested DTO for menu items
    public static class MenuItemDTO {
        private String itemName;
        private int quantity;

        // Constructor
        public MenuItemDTO(String itemName, int quantity) {
            this.itemName = itemName;
            this.quantity = quantity;
        }

        // Getters and setters
        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
