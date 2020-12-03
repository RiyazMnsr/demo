package com.example.demo.service;

import com.example.demo.dto.User;

import java.util.List;

public interface UserService {

    public Integer countUniqueUsers(List<User> users);

    public User getUpdatedUser(List<User> users);
}
