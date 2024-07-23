package com.surabhi.repository;


import com.surabhi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
    // Additional methods if needed
}

