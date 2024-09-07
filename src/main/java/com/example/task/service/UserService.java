package com.example.task.service;

import com.example.task.entity.User;
import com.example.task.model.UserModel;
import com.example.task.model.UserResponse;
import com.example.task.utility.PasswordGeneratorUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task.repository.UserRepository;

import java.util.List;



@Service
public class UserService{

    private static Logger logger= LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;




    public UserResponse registerNewUser(UserModel userModel){
        UserResponse res = new UserResponse(userModel.getUsername(),userModel.getPassword(), userModel.getAddress(),userModel.getEmail());
        logger.trace("class:UserService , method:registerNewUser , message= creating a new user with request [/registerNewUser]");

        if(repository.findById(userModel.getUsername()).isPresent()){
            res.setStatus("Username was already taken " + userModel.getUsername());

               return res;
        }
        User user = new User();
        user.setUserName(userModel.getUsername());
        user.setAddress(userModel.getAddress());
        user.setEmail(userModel.getEmail());
        user.setPassword(PasswordGeneratorUtility.generatePassword(userModel.getUsername())); // password is encoded
        user.setActive(true);
        res.setPassword(PasswordGeneratorUtility.decode(user.getPassword()));
        res.setStatus("User register successfully");
        repository.save(user);
        return res;


    }

    public UserResponse updateUser(UserModel userModel){
        UserResponse res= new UserResponse(userModel.getUsername(),userModel.getPassword(), userModel.getAddress(),userModel.getEmail());
        logger.trace("class:UserService , method:updateUser , message= updating a user with request [/updateUser]");
        if(repository.findById(userModel.getUsername()).isPresent()){
            User user=repository.findById(userModel.getUsername()).get();
            if (!user.isActive()) {
                res.setStatus("User not found");
                return res;
            }
            if(!PasswordGeneratorUtility.decode(user.getPassword()).equals(userModel.getPassword())){
                res.setStatus("Password do not match");
                return res;
            }
            user.setAddress(userModel.getAddress());
            user.setEmail(userModel.getEmail());
            repository.save(user);
            res.setStatus("User Updated successfully");
            return res;

        }
        res.setStatus("User not found");
        return res;


    }


    public UserResponse deleteUser(UserModel userModel){
        logger.trace("class:UserService , method:deleteUser , message= deleting a user with request [/deleteUser]");
        UserResponse res = new UserResponse(userModel.getUsername(),userModel.getPassword(), userModel.getAddress(),userModel.getEmail());

        if(repository.findById(userModel.getUsername()).isPresent()){
            User user=repository.findById(userModel.getUsername()).get();
            if (!user.isActive()) {
                res.setStatus("User not found");
                return res;
                }
            if(!PasswordGeneratorUtility.decode(user.getPassword()).equals(userModel.getPassword())){
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
        logger.trace("class:UserService , method:getUser , message= retrieving all users with request [/getUser]");
        return repository.findAll();
    }

    public UserResponse getUserByName(UserModel userModel){
        logger.trace("class:UserService , method:getUserByName , message= retrieving single user with request [/getUserByName]");
        UserResponse res = new UserResponse(userModel.getUsername(),userModel.getPassword(), userModel.getAddress(), userModel.getEmail());
        if(repository.findById(userModel.getUsername()).isPresent()) {
            User user = repository.findById(userModel.getUsername()).get();
            if (!PasswordGeneratorUtility.decode(user.getPassword()).equals(userModel.getPassword())) {
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

    public UserResponse retrieveUserByPassword(UserModel userModel) {
        logger.trace("class:UserService , method:retrieveUserByPassword , message= retrieving user via password [/retrieveUserByPassword]");

        List<User> users =  repository.findAll();
        UserResponse res = new UserResponse(userModel.getUsername(), userModel.getPassword(), userModel.getAddress(), userModel.getEmail());
        User user = new User();
        if (!user.isActive()) {
            res.setStatus("User not found");
            return res;
        }
        for (User us: users) {
            String decode = PasswordGeneratorUtility.decode(us.getPassword());
            if (decode.equals(userModel.getPassword())) {

                res.setUsername(us.getUserName());
               // res.setPassword(us.getPassword());  will return database encoded password
                res.setAddress(us.getAddress());
                res.setEmail(us.getEmail());
                res.setStatus("Here is the User");
                return res;
            }
        }
        res.setStatus("Password do not match");
        return res;

    }

}