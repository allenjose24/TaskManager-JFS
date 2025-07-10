package com.example.TaskManagement.controller;


import com.example.TaskManagement.model.Users;
import com.example.TaskManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void saveUser(@RequestBody Users user){
        userService.saveUser(user);
    }

    @GetMapping("/view")
    public Iterable<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/get/{id}")
    public Users getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable int id){
        userService.deleteUserById(id);
    }

    @GetMapping("/get/byUserName/{userName}")
    public Users getUserByUserName(@PathVariable String userName){
        return userService.getUserByUserName(userName);
    }

}
