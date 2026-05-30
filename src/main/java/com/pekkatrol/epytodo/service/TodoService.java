package com.pekkatrol.epytodo.service;

import com.pekkatrol.epytodo.dto.TodoResponse;
import com.pekkatrol.epytodo.model.Todo;

import java.util.List;

public interface TodoService {
    List<TodoResponse> findAll();
    TodoResponse findById(Long id);
    List<TodoResponse> findByUserId(Long userId);
    TodoResponse save(Todo todo);
    TodoResponse update(Long id, Todo todo);
    TodoResponse delete(Long id);
}
