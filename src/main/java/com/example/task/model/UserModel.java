package com.example.task.model; //for frontend

import lombok.Data;

@Data
public class UserModel {

    String username;


    String password;

    public UserModel(String userName, String password) {
        this.username=userName;
        this.password=password;
    }
}
