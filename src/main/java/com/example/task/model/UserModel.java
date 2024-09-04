package com.example.task.model; //for frontend

import lombok.Data;

@Data
public class UserModel {


    String username;

    String password;

    String address;

    String email;

    public UserModel(String userName, String password, String address, String email) {
        this.username = userName;
        this.password = password;
        this.address = address;
        this.email = email;

    }

    public UserModel() {
        this.password= password;
    }
}