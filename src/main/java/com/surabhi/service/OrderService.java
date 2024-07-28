package com.surabhi.service;


import com.surabhi.dto.OrderCreationResponseDTO;
import com.surabhi.dto.OrderDTO;
import com.surabhi.dto.OrderSummaryDTO;
import com.surabhi.model.Order;

import java.util.*;
import java.util.List;

public interface OrderService {
    
    OrderCreationResponseDTO createOrder(Order order) throws Exception;
    
    Order getOrderById(Long orderId);
    
    List<OrderSummaryDTO> getAllOrders();
    
    List<Order> getOrdersByUserId(Long userId);
    
    Double getTotalSalesForMonth(int month, int year);
    
    List<Order> getOrdersBetweenDates(Date startDate, Date endDate);
}

