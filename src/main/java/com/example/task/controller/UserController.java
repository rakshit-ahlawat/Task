package com.example.task.controller;

import com.example.task.entity.User;
import com.example.task.model.UserModel;
import com.example.task.model.UserResponse;
import com.example.task.service.UserService;
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
        logger.trace("class:UserController , method:registerNewUser ");
        return res;
    }



    @GetMapping("/getUsers")
    public List<UserModel> getUsers(){
        List<User> user=userService.getUser();
        List<UserModel> response=new ArrayList<>();
        for(User us:user){
            if(us.isActive())
                response.add(new UserModel(us.getUserName(),us.getPassword(),us.getAddress(),us.getEmail()));
        }
        logger.trace("class:UserController , method:getUsers ");
        return response;
    }
    @GetMapping("/getUserByName")
    public UserResponse getUserByName(@RequestBody UserModel userModel){
        UserResponse res = userService.getUserByName(userModel);
        logger.trace("class:UserController , method:getUserByName ");
        return res;
    }

    @PutMapping("/updateUser")
    public UserResponse updateUser(@RequestBody UserModel userModel){
        UserResponse res = userService.updateUser(userModel);
        logger.trace("class:UserController , method:updateUser ");
        return res;
    }

    @DeleteMapping("/deleteUser")
    public UserResponse deleteUser(@RequestBody UserModel userModel){
        UserResponse res= userService.deleteUser(userModel);
        logger.trace("class:UserController , method:deleteUser ");
        return res;

    }
}
