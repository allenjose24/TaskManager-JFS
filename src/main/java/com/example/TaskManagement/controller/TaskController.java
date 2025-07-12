package com.example.TaskManagement.controller;

import com.example.TaskManagement.model.Tasks;
import com.example.TaskManagement.service.TaskService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public void saveTask(@RequestBody Tasks task){
        service.saveTask(task);
    }

    @GetMapping("/view")
    public List<Tasks> getAllTasks(){
        return service.getAllTasks();
    }

    @GetMapping("/get/{id}")
    public Tasks getTaskById(@PathVariable int id){
        return service.getTaskById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTaskById(@PathVariable int id){
        service.deleteTaskById(id);
    }

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
}
