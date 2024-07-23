package com.surabhi.service;


import com.surabhi.model.Order;

import java.util.*;
import java.util.List;

public interface OrderService {
    
    Order createOrder(Order order);
    
    Order getOrderById(Long orderId);
    
    List<Order> getAllOrders();
    
    List<Order> getOrdersByUserId(Long userId);
    
    Double getTotalSalesForMonth(int month, int year);
    
    List<Order> getOrdersBetweenDates(Date startDate, Date endDate);
}

