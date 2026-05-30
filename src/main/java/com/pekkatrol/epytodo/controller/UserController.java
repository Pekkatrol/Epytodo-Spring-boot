package com.pekkatrol.epytodo.controller;

import com.pekkatrol.epytodo.model.User;
import com.pekkatrol.epytodo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user) {
        String token = userService.register(user);
        return Map.of("token", token);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        String token = userService.login(user);
        return Map.of("token", token);
    }
    
    @GetMapping("/api/users")
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/api/users/{id}")
    public User getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/api/users/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/api/users/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}