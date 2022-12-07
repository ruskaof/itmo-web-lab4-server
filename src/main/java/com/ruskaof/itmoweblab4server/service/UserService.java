package com.ruskaof.itmoweblab4server.service;

import com.ruskaof.itmoweblab4server.model.User;

import java.util.List;

public interface UserService {
    boolean register(User user);

    List<User> getAll();
    User findByUsername(String username);
    User findById(Integer id);
    void delete(Integer id);
}
