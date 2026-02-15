package com.meswright.ja.taskmanager.ui.client;

import com.meswright.dto.TestDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class BackendApiClient {

    private final RestClient restClient;

    public TestDtoV1 test() {
        return restClient.get().uri("http://localhost:8081/task-manager/api/v1/test").retrieve().body(TestDtoV1.class);
    }

}
