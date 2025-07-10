package com.example.TaskManagement.service;

import com.example.TaskManagement.model.Users;
import com.example.TaskManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    //create
    public void saveUser(Users user){
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        repository.save(user);
        System.out.println("user saved");
    }

    //read
    public List<Users> getAllUsers(){
        return repository.findAll();
    }

    //get
    public Users getUserById(int id){
        return repository.findById(id).get();
    }

    //byUserName
    public Users getUserByUserName(String userName){
        return repository.findByUserName(userName);
    }

    //delete
    public void deleteUserById(int id){
        repository.deleteById(id);
    }
}
