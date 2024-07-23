package com.surabhi.repository;

import com.surabhi.model.OrderDetail;
import com.surabhi.model.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
    
    // Additional methods if needed
}

