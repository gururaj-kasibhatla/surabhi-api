package com.surabhi.repository;


import com.surabhi.model.Order;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByOrderDateBetween(Date startDate, Date endDate);
//	List<Order> findByUserId(Long userId);
}
