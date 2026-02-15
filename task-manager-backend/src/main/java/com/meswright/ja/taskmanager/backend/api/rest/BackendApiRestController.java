package com.meswright.ja.taskmanager.backend.api.rest;

import com.meswright.dto.TestDtoV1;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class BackendApiRestController {

    @GetMapping("task-manager/api/v1/test")
    public TestDtoV1 test() {
        return new TestDtoV1(LocalDateTime.now());
    }

}
