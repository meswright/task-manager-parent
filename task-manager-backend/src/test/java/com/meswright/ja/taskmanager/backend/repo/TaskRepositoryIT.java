package com.meswright.ja.taskmanager.backend.repo;

import com.meswright.ja.taskmanager.backend.entity.TaskEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TaskRepositoryIT {

    @Autowired
    private TaskRepository taskRepo;

    @Test
    public void whenCalledSave_thenCorrectNumberOfTasks() {
        TaskEntity task = new TaskEntity();
        task.setId(UUID.randomUUID());
        task.setTitle("Test Task");
        task.setDescription("Test Task");
        task.setStatus("Active");
        taskRepo.save(task);
        List<TaskEntity> users = taskRepo.findAll();
        assertThat(users.size()).isEqualTo(1);
    }
}