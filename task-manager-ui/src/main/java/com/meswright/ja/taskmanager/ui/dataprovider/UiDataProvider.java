package com.meswright.ja.taskmanager.ui.dataprovider;

import com.meswright.ja.taskmanager.ui.client.BackendApiClient;
import com.meswright.ja.taskmanager.ui.pojo.TaskUio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UiDataProvider {

    private final BackendApiClient client;

    public List<TaskUio> getTasks() {
        List<TaskUio> tasks = new ArrayList<>();
        TaskUio taskUio = new TaskUio();
        taskUio.setId(UUID.randomUUID());
        taskUio.setTitle("Test Task 1");
        taskUio.setDescription("This is a test task 1");
        taskUio.setStatus("Status 1");
        tasks.add(taskUio);
        taskUio = new TaskUio();
        taskUio.setId(UUID.randomUUID());
        taskUio.setTitle("Test Task 2");
        taskUio.setDescription("This is a test task 2");
        taskUio.setStatus("Status 2");
        tasks.add(taskUio);
        taskUio = new TaskUio();
        taskUio.setId(UUID.randomUUID());
        taskUio.setTitle("Test Task 3");
        taskUio.setDescription("This is a test task 3");
        taskUio.setStatus("Status 3");
        tasks.add(taskUio);
        return tasks;
    }
}
