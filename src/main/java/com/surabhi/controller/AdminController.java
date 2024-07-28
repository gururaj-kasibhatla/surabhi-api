package com.surabhi.controller;


import com.surabhi.dto.OrderCreationResponseDTO;
import com.surabhi.dto.OrderSummaryDTO;
import com.surabhi.model.MenuItem;
import com.surabhi.model.Order;
import com.surabhi.service.MenuItemService;
import com.surabhi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
    
    @Autowired
    private MenuItemService menuItemService;
    
    @Autowired
    private OrderService orderService;
    
    @PostMapping("/menuItems")
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem) {
        MenuItem newMenuItem = menuItemService.createMenuItem(menuItem);
        return new ResponseEntity<>(newMenuItem, HttpStatus.CREATED);
    }
    
    @GetMapping("/menuItems/{menuItemId}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long menuItemId) {
        MenuItem menuItem = menuItemService.getMenuItemById(menuItemId);
        if (menuItem != null) {
            return new ResponseEntity<>(menuItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/menuItems")
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        List<MenuItem> menuItems = menuItemService.getAllMenuItems();
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }
    
    @PutMapping("/menuItems/{menuItemId}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long menuItemId, @RequestBody MenuItem menuItem) {
        MenuItem updatedMenuItem = menuItemService.updateMenuItem(menuItemId, menuItem);
        if (updatedMenuItem != null) {
            return new ResponseEntity<>(updatedMenuItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/menuItems/{menuItemId}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long menuItemId) {
        menuItemService.deleteMenuItem(menuItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/orders")
    public ResponseEntity<List<OrderSummaryDTO>> getAllOrders() {
        List<OrderSummaryDTO> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    
    @GetMapping("/orders/totalSales/{month}/{year}")
    public ResponseEntity<Double> getTotalSalesForMonth(@PathVariable int month, @PathVariable int year) {
        Double totalSales = orderService.getTotalSalesForMonth(month, year);
        return new ResponseEntity<>(totalSales, HttpStatus.OK);
    }
    
    @GetMapping("/orders/betweenDates")
    public ResponseEntity<List<Order>> getOrdersBetweenDates(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                                             @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
        List<Order> orders = orderService.getOrdersBetweenDates(startDate, endDate);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    
    @PostMapping("/orders")
    public ResponseEntity<OrderCreationResponseDTO> createOrder(@RequestBody Order order) {
        try {
            OrderCreationResponseDTO createdOrder = orderService.createOrder(order);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (Exception e) {
        	System.out.println("Exception "+e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    
}

