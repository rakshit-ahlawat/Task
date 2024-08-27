package com.example.task.entity;




import jakarta.persistence.*;

import lombok.Data;



@Data
@Table(name = "Users")
@Entity
public class User{
    // make a method to specify Unique Username which can not be taken again after the deletetion
    @Id
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="active")
    private boolean active;


}