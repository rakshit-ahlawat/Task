package com.example.task.model;  //for JSON response



import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Data
public class UserResponse {
    private static Logger logger = LoggerFactory.getLogger(UserResponse.class);


    String username;
    String password;
    String address;
    String email;
    String status;


    public UserResponse(String userName, String password, String address, String email) {

        logger.trace("class:UserResponse , method:UserResponse ");
        this.username=userName;
        this.password=password;
        this.address=address;
        this.email=email;
        this.status="Success";

    }

}
