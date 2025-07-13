package com.example.TaskManagement.controller;

import com.example.TaskManagement.dto.TaskDeadlineDTO;
import com.example.TaskManagement.dto.TaskUserDTO;
import com.example.TaskManagement.model.Tasks;
import com.example.TaskManagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public void saveTask(@RequestBody Tasks task){
        service.saveTask(task);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public void updateTask(@RequestBody Tasks task){
        service.saveTask(task);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/view")
    public List<Tasks> getAllTasks(){
        return service.getAllTasks();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    public Tasks getTaskById(@PathVariable int id){
        return service.getTaskById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteTaskById(@PathVariable int id){
        service.deleteTaskById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/assign/{taskId}/user/{userId}")
    public ResponseEntity<String> assignUserToTask(@PathVariable int taskId, @PathVariable int userId) {
        boolean success = service.assignUserToTask(taskId, userId);

        if (success) {
            return ResponseEntity.ok("User assigned to task successfully");
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Task or User not found. Failed to assign user to task.");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/task-user-view")
    public List<TaskUserDTO> getTaskUserView() {
        List<Tasks> allTasks = service.getAllTasks();

        return allTasks.stream()
                .map(task -> new TaskUserDTO(
                        task.getTaskId(),
                        task.getTaskDescription(),
                        task.getStartDate(),
                        task.getEndDate(),
                        task.getUser().getUserId(),
                        task.getUser().getUserName()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{userId}")
    public List<TaskDeadlineDTO> getTasksSortedByDeadline(@PathVariable int userId) {
        return service.getTasksByUserSortedByDeadline(userId).stream()
                .map(task -> new TaskDeadlineDTO(
                        task.getTaskId(),
                        task.getTaskDescription(),
                        task.getStartDate(),
                        task.getEndDate()
                ))
                .collect(Collectors.toList());
    }

}
