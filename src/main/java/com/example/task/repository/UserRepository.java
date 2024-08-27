package com.example.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.task.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {


}