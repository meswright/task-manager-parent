package com.meswright.ja.taskmanager.backend.crud;

import com.meswright.ja.taskmanager.backend.entity.TaskEntity;
import com.meswright.ja.taskmanager.backend.exeption.TaskException;
import com.meswright.ja.taskmanager.backend.exeption.TaskIdAleadyExistsException;
import com.meswright.ja.taskmanager.backend.exeption.TaskNotFoundException;
import com.meswright.ja.taskmanager.backend.repo.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskCrudHandler {

    private final TaskRepository taskRepository;

    public TaskEntity create(TaskEntity taskEntity) throws TaskIdAleadyExistsException {
        if (taskRepository.existsById(taskEntity.getId())) {
            throw new TaskIdAleadyExistsException("A Task already exists with this ID");
        }
        return taskRepository.saveAndFlush(taskEntity);
    }

    public TaskEntity read(UUID id) throws TaskNotFoundException {
        Optional<TaskEntity> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            return optionalTask.get();
        }
        throw new TaskNotFoundException("Task with given UUID does not exist");
    }

    public List<TaskEntity> readAll() {
        return taskRepository.findAll();
    }

    public TaskEntity update(TaskEntity taskEntity) throws TaskNotFoundException {
        Optional<TaskEntity> optionalExistingTask = taskRepository.findById(taskEntity.getId());
        if (optionalExistingTask.isEmpty()) {
            throw new TaskNotFoundException("Task to update does not exist");
        }
        TaskEntity existingTask = optionalExistingTask.get();
        existingTask.setTitle(taskEntity.getTitle());
        existingTask.setDescription(taskEntity.getDescription());
        existingTask.setStatus(taskEntity.getStatus());
        return taskRepository.saveAndFlush(existingTask);
    }

    public void delete(UUID id) {
        taskRepository.deleteById(id);
    }

    public void delete(Set<UUID> ids) {
        taskRepository.deleteAllById(ids);
    }

}
