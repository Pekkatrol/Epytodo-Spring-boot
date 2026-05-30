package com.pekkatrol.epytodo.controller;

import com.pekkatrol.epytodo.dto.TodoResponse;
import com.pekkatrol.epytodo.model.Todo;
import com.pekkatrol.epytodo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public List<TodoResponse> getAll() {
        return todoService.findAll();
    }

    @GetMapping("/{id}")
    public TodoResponse getById(@PathVariable Long id) {
        return todoService.findById(id);
    }

    @GetMapping("/user/{userId}")
    public List<TodoResponse> getByUser(@PathVariable Long userId) {
        return todoService.findByUserId(userId);
    }

    @PostMapping
    public TodoResponse create(@RequestBody Todo todo) {
        return todoService.save(todo);
    }

    @PutMapping("/{id}")
    public TodoResponse update(@PathVariable Long id, @RequestBody Todo todo) {
        return todoService.update(id, todo);
    }

    @DeleteMapping("/{id}")
    public TodoResponse delete(@PathVariable Long id) {
        return todoService.delete(id);
    }
}
