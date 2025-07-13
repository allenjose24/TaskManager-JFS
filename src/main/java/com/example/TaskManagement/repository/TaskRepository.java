package com.example.TaskManagement.repository;

import com.example.TaskManagement.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks, Integer> {

    List<Tasks> findByUser_UserIdOrderByEndDateAsc(int userId);

}
