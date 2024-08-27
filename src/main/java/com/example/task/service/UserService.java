package com.example.task.service;

import com.example.task.entity.User;
import com.example.task.model.UserModel;
import com.example.task.model.UserResponse;
import com.example.task.utility.PasswordGeneratorUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task.repository.UserRepository;

import java.util.List;


@Service
public class UserService{

    @Autowired
    private UserRepository repository;

//    public UserModel registerNewUser(UserModel username){
//        if (repository.findById(String.valueOf(username.getUsername())).isPresent()){
//            return username;
//        }
//        return username;
//    }
    public UserResponse registerNewUser(String username){
        UserResponse res=new UserResponse(username);

        if(repository.findById(username).isPresent()){
            res.setStatus("Username was already taken "+username);

               return res;
        }
        User user = new User();
        user.setUserName(username);
        user.setPassword(PasswordGeneratorUtility.generatePassword());
        user.setActive(true);
        res.setPassword(user.getPassword());
        res.setStatus("User register successfully");
        repository.save(user);
        return res;


    }

//    public String updateUser(UserModel userModel){
//        UserResponse res= new UserResponse(userModel.getUsername(),userModel.getPassword();
//        return "User Updated successful";
//    }

    public UserResponse deleteUser(UserModel userModel){
        UserResponse res = new UserResponse(userModel.getUsername(),userModel.getPassword());

        if(repository.findById(userModel.getUsername()).isPresent()){
            User user=repository.findById(userModel.getUsername()).get();
            if(!user.getPassword().equals(userModel.getPassword())){
                res.setStatus("Password do not match");
                return res;
            }

            user.setActive(false);
            repository.save(user);

            return res;
        }
        res.setStatus("User not found");
        return res;
    }

    public List<User> getUser(){
        return repository.findAll();
    }

    public UserResponse getUserByName(UserModel userModel){
        UserResponse res = new UserResponse(userModel.getUsername(),userModel.getPassword());
        if(repository.findById(userModel.getUsername()).isPresent()) {
            User user = repository.findById(userModel.getUsername()).get();
            if (!user.getPassword().equals(userModel.getPassword())) {
                res.setStatus("Password do not match");
                return res;
            }
            if (user.isActive()) {
                res.setStatus("User found Successfully");
                return res;
            }
        }
        res.setStatus("User Not Found");
        return res;
    }


}