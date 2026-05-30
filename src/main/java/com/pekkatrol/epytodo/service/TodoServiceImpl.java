package com.pekkatrol.epytodo.service;

import com.pekkatrol.epytodo.dto.TodoResponse;
import com.pekkatrol.epytodo.model.Todo;
import com.pekkatrol.epytodo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    private TodoResponse toResponse(Todo todo) {
        String status = switch (todo.getStatus()) {
            case NOT_STARTED -> "not started";
            case TODO -> "todo";
            case IN_PROGRESS -> "in progress";
            case DONE -> "done";
        };
        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getCreatedAt(),
                todo.getDueTime(),
                status,
                todo.getUser().getId()
        );
    }

    @Override
    public List<TodoResponse> findAll() {
        return todoRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public TodoResponse findById(Long id) {
        return toResponse(todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found")));
    }

    @Override
    public List<TodoResponse> findByUserId(Long userId) {
        return todoRepository.findByUserId(userId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public TodoResponse save(Todo todo) {
        return toResponse(todoRepository.save(todo));
    }

    @Override
    public TodoResponse update(Long id, Todo todo) {
        Todo existing = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        existing.setTitle(todo.getTitle());
        existing.setDescription(todo.getDescription());
        existing.setDueTime(todo.getDueTime());
        existing.setStatus(todo.getStatus());
        existing.setUser(todo.getUser());
        return toResponse(todoRepository.save(existing));
    }

    @Override
    public TodoResponse delete(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todoRepository.deleteById(id);
        return toResponse(todo);
    }
}
