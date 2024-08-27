package com.example.task.model;  //for JSON response

import lombok.Data;

@Data
public class UserResponse {
    String username;
    String password;
    String status;

    public UserResponse(String username) {
        this.username = username;
    }

    public UserResponse(String userName, String password) {
        this.username=userName;
        this.password=password;
        this.status="Success";
    }
}
