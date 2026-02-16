package com.meswright.ja.taskmanager.ui.client;

import com.meswright.dto.TaskDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@RequiredArgsConstructor
/**
 * Responsible for being the REST Client to the Task Management Backend for the UI.
 */
public class BackendApiClient {

    private static final String URL = "http://localhost:8081/task-manager/api/v1/tasks";
    private static final String URL_STRING_TEMPLATE = URL + "/%s";

    private final RestClient restClient;

    public void createTask(TaskDtoV1 dto) {
        restClient.post()
                .uri(URL_STRING_TEMPLATE.formatted(dto.id()))
                .contentType(APPLICATION_JSON)
                .body(dto)
                .retrieve()
                .toBodilessEntity();
    }

    public List<TaskDtoV1> getAllTasks() {
        return restClient.get()
                .uri(URL)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public void updateTask(TaskDtoV1 dto) {
        restClient.put()
                .uri(URL_STRING_TEMPLATE.formatted(dto.id()))
                .contentType(APPLICATION_JSON)
                .body(dto)
                .retrieve()
                .toBodilessEntity();

    }

    public void deleteTask(TaskDtoV1 dto) {
        restClient.delete()
                .uri(URL_STRING_TEMPLATE.formatted(dto.id()))
                .retrieve()
                .toBodilessEntity();
    }

    public void deleteTasks(List<TaskDtoV1> dtos) {
      restClient.post()
              .uri(URL_STRING_TEMPLATE.formatted("bulk-delete"))
                      .contentType(APPLICATION_JSON)
                      .body(dtos.stream().map(TaskDtoV1::id).collect(Collectors.toSet()))
                      .retrieve()
                      .toBodilessEntity();
    }
}
