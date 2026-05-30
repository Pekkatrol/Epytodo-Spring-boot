package com.pekkatrol.epytodo.service;

import com.pekkatrol.epytodo.model.User;

import java.util.List;

public interface UserService {
    String register(User user);
    String login(User user);
    List<User> findAll();
    User findById(Long id);
    User findByEmail(String email);
    User save(User user);
    User update(Long id, User user);
    void delete(Long id);
}