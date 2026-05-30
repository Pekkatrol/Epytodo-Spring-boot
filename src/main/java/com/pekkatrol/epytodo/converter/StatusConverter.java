package com.pekkatrol.epytodo.converter;

import com.pekkatrol.epytodo.model.Todo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import static com.pekkatrol.epytodo.model.Todo.Status.NOT_STARTED;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Todo.Status, String> {

    @Override
    public String convertToDatabaseColumn(Todo.Status status) {
        if (status == null) return null;
        return switch (status) {
            case NOT_STARTED -> "not started";
            case TODO -> "todo";
            case IN_PROGRESS -> "in progress";
            case DONE -> "done";
        };
    }

    @Override
    public Todo.Status convertToEntityAttribute(String value) {
        if (value == null) return null;
        return switch (value) {
            case "not started" -> Todo.Status.NOT_STARTED;
            case "todo" -> Todo.Status.TODO;
            case "in progress" -> Todo.Status.IN_PROGRESS;
            case "done" -> Todo.Status.DONE;
            default -> throw new IllegalArgumentException("Unknown status: " + value);
        };
    }
}
