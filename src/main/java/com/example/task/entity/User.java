package com.example.task.entity;

import jakarta.persistence.*;

import lombok.Data;


@Data
@Table(name = "Users")
@Entity
public class User{
    //  specify Unique Username which can not be taken again after the deletion



    @Id
    @Column
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name = "address")
    private String address;


    @Column(name = "email")
    private String email;


    @Column(name="active")
    private boolean active;


}