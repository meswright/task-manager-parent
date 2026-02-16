package com.meswright.ja.taskmanager.ui.dataprovider;

import com.meswright.ja.taskmanager.ui.client.BackendApiClient;
import com.meswright.ja.taskmanager.ui.mapper.TaskMapper;
import com.meswright.ja.taskmanager.ui.pojo.TaskUio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UiDataProvider {

    private final BackendApiClient client;
    private final TaskMapper taskMapper;

    public List<TaskUio> getTasks() {
        return taskMapper.toUios(client.getAllTasks());
    }

    public void createTask(TaskUio task) {
        client.createTask(taskMapper.toDto(task));
    }

    public void updateTask(TaskUio task) {
        client.updateTask(taskMapper.toDto(task));
    }

    public void deleteTask(TaskUio task) {
        client.deleteTask(taskMapper.toDto(task));
    }

    public void deleteTasks(List<TaskUio> tasks) {
        client.deleteTasks(taskMapper.toDtos(tasks));
    }
}
