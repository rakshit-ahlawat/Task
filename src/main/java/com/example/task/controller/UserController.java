package com.example.task.controller;

import com.example.task.entity.User;
import com.example.task.model.UserModel;
import com.example.task.model.UserResponse;
import com.example.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //    @PostMapping("/registerNewUser")
//    public String registerUser(@RequestParam String username){
//       String res=userService.registerNewUser(username);
//        return res;
//    }
    @PostMapping("/registerNewUser")
    public UserResponse registerUser(@RequestBody UserModel userModel){
        String username = userModel.getUsername();
        UserResponse res = userService.registerNewUser(username);
        return res;
    }



    @GetMapping("/getUsers")
    public List<UserModel> getUsers(){
        List<User> user=userService.getUser();
        List<UserModel> response=new ArrayList<>();
        for(User us:user){
            if(us.isActive())
                response.add(new UserModel(us.getUserName(),us.getPassword()));
        }
        return response;
    }
    @GetMapping("/getUserByName")
    public UserResponse getUserByName(@RequestBody UserModel userModel){
        UserResponse res = userService.getUserByName(userModel);

        return res;
    }

//    @PutMapping("/updateUser")
//    public User updateUser(@RequestBody UserModel userModel){
//        String res= userService.updateUser(userModel);
//
//        return userService.getUserByUserName(userModel.getUsername());
//    }

    @DeleteMapping("/deleteUser")
    public UserResponse deleteUser(@RequestBody UserModel userModel){
        UserResponse res= userService.deleteUser(userModel);

        return res;

    }
}
