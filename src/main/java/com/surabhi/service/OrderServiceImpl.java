package com.surabhi.service;


import com.surabhi.model.Order;
import com.surabhi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
	
    
    @Autowired
    private OrderRepository orderRepository;
    
    
    
    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
    
    @Override
    public Order getOrderById(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        return optionalOrder.orElse(null);
    }
    
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        // Implement if needed
        return null;
    }
    
    @Override
    public List<Order> getOrdersBetweenDates(Date startDate, Date endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate);
    }
    
    @Override
    public Double getTotalSalesForMonth(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1); // Calendar months are zero-based
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        long startOfMonth = calendar.getTimeInMillis();
        
        calendar.add(Calendar.MONTH, 1);
        long startOfNextMonth = calendar.getTimeInMillis();
        
        List<Order > orders = getAllOrders();
        Double totalSales = orders.stream().mapToDouble(Order::getTotalAmount).sum();
        
        return totalSales;
    }
}

