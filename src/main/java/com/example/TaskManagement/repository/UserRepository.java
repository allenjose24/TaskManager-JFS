package com.example.TaskManagement.repository;

import com.example.TaskManagement.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUserName(String userName);
}
