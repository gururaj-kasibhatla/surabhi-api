package com.surabhi.service;

import com.surabhi.dto.OrderCreationResponseDTO;
import com.surabhi.dto.OrderDTO;
import com.surabhi.dto.OrderDetailDTO;
import com.surabhi.dto.OrderSummaryDTO;
import com.surabhi.dto.OrderSummaryDTO.MenuItemDTO;
import com.surabhi.model.MenuItem;
import com.surabhi.model.Order;
import com.surabhi.model.OrderDetail;
import com.surabhi.model.OrderDetailId;
import com.surabhi.repository.MenuItemRepository;
import com.surabhi.repository.OrderRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;


    @Override
    public Order getOrderById(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        return optionalOrder.orElse(null);
    }

    @Override
    public List<OrderSummaryDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> new OrderSummaryDTO(
                        order.getOrderId(),
                        order.getUser().getUserId(),
                        order.getTotalAmount(),
                        order.getOrderDate(),
                        mapOrderDetails(order.getOrderDetails())))
                .collect(Collectors.toList());
    }

    private List<MenuItemDTO> mapOrderDetails(List<OrderDetail> orderDetails) {
        return orderDetails.stream()
                .map(detail -> new MenuItemDTO(
                        detail.getMenuItem().getName(),
                        detail.getQuantity()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getUser().getUserId().equals(userId))
                .collect(Collectors.toList());
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

        Date startOfMonth = calendar.getTime();

        calendar.add(Calendar.MONTH, 1);
        Date startOfNextMonth = calendar.getTime();

        List<Order> orders = orderRepository.findByOrderDateBetween(startOfMonth, startOfNextMonth);
        Double totalSales = orders.stream().mapToDouble(Order::getTotalAmount).sum();

        return totalSales;
    }
    
    @Transactional
    @Override
    public OrderCreationResponseDTO createOrder(Order order) throws Exception {
        // Validate order details
        if (order == null || order.getOrderDetails() == null || order.getOrderDetails().isEmpty()) {
            throw new IllegalArgumentException("Order details cannot be null or empty");
        }

        // Save order and order details
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            MenuItem menuItem = menuItemRepository.findById(orderDetail.getMenuItem().getItemId())
                    .orElseThrow(() -> new Exception("Menu item not found: " + orderDetail.getMenuItem().getItemId()));
            orderDetail.setMenuItem(menuItem);
            orderDetail.setOrder(order);
            
            // Set OrderDetailId
            OrderDetailId orderDetailId = new OrderDetailId();
            orderDetailId.setOrderId(order.getOrderId());
            orderDetailId.setItemId(menuItem.getItemId());
            orderDetail.setId(orderDetailId);
        }

        return convertToOrderCreationResponseDTO(orderRepository.save(order));
    }

    private OrderCreationResponseDTO convertToOrderCreationResponseDTO(Order order) {
        OrderCreationResponseDTO dto = new OrderCreationResponseDTO();
        dto.setOrderId(order.getOrderId());
        dto.setUserId(order.getUser().getUserId());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalAmount(order.getTotalAmount());

        List<OrderDetailDTO> orderDetails = order.getOrderDetails().stream()
            .map(detail -> {
                OrderDetailDTO detailDTO = new OrderDetailDTO();
                detailDTO.setItemId(detail.getMenuItem().getItemId());
                detailDTO.setQuantity(detail.getQuantity());
                return detailDTO;
            })
            .collect(Collectors.toList());
        dto.setOrderDetails(orderDetails);

        return dto;
    }



}
