package com.meswright.ja.taskmanager.backend.api.rest;

import com.meswright.dto.TaskDtoV1;
import com.meswright.ja.taskmanager.backend.crud.TaskCrudHandler;
import com.meswright.ja.taskmanager.backend.exeption.TaskIdAleadyExistsException;
import com.meswright.ja.taskmanager.backend.exeption.TaskNotFoundException;
import com.meswright.ja.taskmanager.backend.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("task-manager/api/v1/tasks")
@RequiredArgsConstructor
public class BackendApiRestController {

    private final TaskCrudHandler taskCrudHandler;
    private final TaskMapper taskMapper;

    @PostMapping("/{id}")
    public TaskDtoV1 postTask(@PathVariable UUID id, @RequestBody TaskDtoV1 dto) {
        validateDtoIds(id, dto);
        try {
            return taskMapper.toDto(taskCrudHandler.create(taskMapper.toEntity(dto)));
        } catch (TaskIdAleadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping()
    public List<TaskDtoV1> getAllTasks() {
        return taskMapper.toDtos(taskCrudHandler.readAll());
    }

    @GetMapping("/{id}")
    public TaskDtoV1 getTask(@PathVariable UUID id) {
        try {
            return taskMapper.toDto(taskCrudHandler.read(id));
        } catch (TaskNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public TaskDtoV1 updateTask(@PathVariable UUID id, @RequestBody TaskDtoV1 dto) {
        validateDtoIds(id, dto);
        try {
            return taskMapper.toDto(taskCrudHandler.update(taskMapper.toEntity(dto)));
        } catch (TaskNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable UUID id) {
        taskCrudHandler.delete(id);
    }

    // this is not a DELETE because of URI length limits
    @PostMapping("/bulk-delete")
    public void deleteTask(@RequestBody Set<UUID> ids) {
        taskCrudHandler.delete(ids);
    }

    private static void validateDtoIds(UUID id, TaskDtoV1 dto) {
        if (!id.equals(dto.id())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non matching IDs in HTTP request");
        }
    }

}
