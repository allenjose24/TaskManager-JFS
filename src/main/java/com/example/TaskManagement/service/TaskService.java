package com.example.TaskManagement.service;

import com.example.TaskManagement.model.Tasks;
import com.example.TaskManagement.model.Users;
import com.example.TaskManagement.repository.TaskRepository;
import com.example.TaskManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final UserRepository userRepository;


    @Autowired
    public TaskService(TaskRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    //Create
    public void saveTask(Tasks task){
        repository.save(task);
    }

    //Read
    public List<Tasks> getAllTasks(){
        return repository.findAll();
    }

    //Get
    public Tasks getTaskById(int id){
        return repository.findById(id).orElse(null);
    }

    //delete
    public void deleteTaskById(int id){
        repository.deleteById(id);
    }

    public boolean assignUserToTask(int taskId, int userId) {
        Optional<Tasks> optionalTask = repository.findById(taskId);
        Optional<Users> optionalUser = userRepository.findById(userId);

        if (optionalTask.isPresent() && optionalUser.isPresent()) {
            Tasks task = optionalTask.get();
            Users user = optionalUser.get();

            task.setUser(user);  // set user
            repository.save(task);  // save updated task
            return true;
        }
        return false;
    }
}
