package com.example.task.controller;

import com.example.task.entity.User;
import com.example.task.model.UserModel;
import com.example.task.model.UserResponse;
import com.example.task.service.UserService;
import com.example.task.utility.PasswordGeneratorUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;



@RestController
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
//    @PostMapping("/registerNewUser")
//    public String registerUser(@RequestParam String username){
//       String res=userService.registerNewUser(username);
//        return res;
//    }

    @PostMapping("/registerNewUser")
    public UserResponse registerUser(@RequestBody UserModel userModel){
        UserResponse res = userService.registerNewUser(userModel);
        logger.trace("class:UserController , method:registerNewUser , message= creating a new user with request [/registerNewUser]");
        return res;
    }



    @GetMapping("/getUsers")
    public List<UserModel> getUsers(){
        List<User> user=userService.getUser();
        List<UserModel> response=new ArrayList<>();
        for(User us:user){
            if(us.isActive())
                response.add(new UserModel(us.getUserName(),(PasswordGeneratorUtility.decode(us.getPassword())),us.getAddress(),us.getEmail())); // PasswordUtility.decode()
        }
        logger.trace("class:UserController , method:getUsers , message= retrieving all users with request [/getUsers]");
        return response;
    }

    @GetMapping("/retrieveUserByPassword")
    public UserResponse retrieveUserByPassword(@RequestBody UserModel userModel){
        UserResponse res = userService.retrieveUserByPassword(userModel);
        logger.trace("class:UserController , method:retrieveUserByPassword , message= retrieving user by password [/retrieveUserPassword]");
        return res;
    }

    @GetMapping("/getUserByName")
    public UserResponse getUserByName(@RequestBody UserModel userModel){
        UserResponse res = userService.getUserByName(userModel);
        logger.trace("class:UserController , method:getUserByName , message= retrieving a single user with request [/getUserByName]");
        return res;
    }

    @PutMapping("/updateUser")
    public UserResponse updateUser(@RequestBody UserModel userModel){
        UserResponse res = userService.updateUser(userModel);
        logger.trace("class:UserController , method:updateUser , message= updating a user with request [/UpdateUser]");
        return res;
    }

    @DeleteMapping("/deleteUser")
    public UserResponse deleteUser(@RequestBody UserModel userModel){
        UserResponse res= userService.deleteUser(userModel);
        logger.trace("class:UserController , method:deleteUser , message= deleting a user with request [/deleteUser]");
        return res;

    }
}
