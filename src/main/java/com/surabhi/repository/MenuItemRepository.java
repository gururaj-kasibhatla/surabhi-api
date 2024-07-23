package com.surabhi.repository;


import com.surabhi.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    
    // Additional methods if needed
}
