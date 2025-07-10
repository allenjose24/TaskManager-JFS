package com.example.TaskManagement.repository;

import com.example.TaskManagement.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Tasks, Integer> {

}
