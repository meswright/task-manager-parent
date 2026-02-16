package com.meswright.ja.taskmanager.backend.api.rest;

import com.meswright.dto.TaskDtoV1;
import com.meswright.ja.taskmanager.backend.crud.TaskCrudHandler;
import com.meswright.ja.taskmanager.backend.entity.TaskEntity;
import com.meswright.ja.taskmanager.backend.mapper.TaskMapper;
import com.meswright.ja.taskmanager.backend.mapper.TaskMapperImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BackendApiRestControllerTest {

    private static final UUID TASK_ID = UUID.randomUUID();
    private static final UUID DIFFERENT_TASK_ID = UUID.randomUUID();
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String STATUS = "status";
    private static final TaskDtoV1 TASK_DTO = new TaskDtoV1(TASK_ID, TITLE, DESCRIPTION, STATUS);
    private static final TaskEntity TASK_ENTITY = new TaskEntity();
    private static final TaskDtoV1 TASK_DTO_DIFFERENT_ID = new TaskDtoV1(DIFFERENT_TASK_ID, TITLE, DESCRIPTION, STATUS);
    private static final List<TaskDtoV1> TASK_DTOS = List.of(TASK_DTO);
    private static final List<TaskEntity> TASK_ENTITIES = List.of(TASK_ENTITY);

    static {
        TASK_ENTITY.setId(TASK_ID);
        TASK_ENTITY.setTitle(TITLE);
        TASK_ENTITY.setDescription(DESCRIPTION);
        TASK_ENTITY.setStatus(STATUS);
    }

    @Mock
    TaskCrudHandler taskCrudHandler;
    TaskMapper taskMapper = new TaskMapperImpl();

    private BackendApiRestController classUnderTest;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        classUnderTest = new BackendApiRestController(taskCrudHandler, taskMapper);
        lenient().doReturn(TASK_ENTITY).when(taskCrudHandler).create(TASK_ENTITY);
        lenient().doReturn(TASK_ENTITY).when(taskCrudHandler).read(TASK_ID);
        lenient().doReturn(TASK_ENTITIES).when(taskCrudHandler).readAll();
        lenient().doReturn(TASK_ENTITY).when(taskCrudHandler).update(TASK_ENTITY);
    }

    @SneakyThrows
    @Test
    @DisplayName("POST Task : Sunny Day")
    void postTask() {
        var result = classUnderTest.postTask(TASK_ID, TASK_DTO);
        verify(taskCrudHandler).create(TASK_ENTITY);
        assertThat(result.id()).isEqualTo(TASK_ID);
        assertThat(result.title()).isEqualTo(TITLE);
        assertThat(result.description()).isEqualTo(DESCRIPTION);
        assertThat(result.status()).isEqualTo(STATUS);
    }

    @SneakyThrows
    @Test
    @DisplayName("GET all Tasks : Sunny Day")
    void getAllTasks() {
        var result = classUnderTest.getAllTasks();
        verify(taskCrudHandler).readAll();
        assertThat(result.size()).isEqualTo(TASK_DTOS.size());
        assertThat(result.getFirst().id()).isEqualTo(TASK_ID);
        assertThat(result.getFirst().title()).isEqualTo(TITLE);
        assertThat(result.getFirst().description()).isEqualTo(DESCRIPTION);
        assertThat(result.getFirst().status()).isEqualTo(STATUS);
    }

    @SneakyThrows
    @Test
    @DisplayName("GET Task : Sunny Day")
    void getTask() {
        var result = classUnderTest.postTask(TASK_ID, TASK_DTO);
        verify(taskCrudHandler).create(TASK_ENTITY);
        assertThat(result.id()).isEqualTo(TASK_ID);
        assertThat(result.title()).isEqualTo(TITLE);
        assertThat(result.description()).isEqualTo(DESCRIPTION);
        assertThat(result.status()).isEqualTo(STATUS);
    }

    @SneakyThrows
    @Test
    @DisplayName("PUT Task : Sunny Day")
    void updateTask() {
        var result = classUnderTest.updateTask(TASK_ID, TASK_DTO);
        verify(taskCrudHandler).update(TASK_ENTITY);
        assertThat(result.id()).isEqualTo(TASK_ID);
        assertThat(result.title()).isEqualTo(TITLE);
        assertThat(result.description()).isEqualTo(DESCRIPTION);
        assertThat(result.status()).isEqualTo(STATUS);
    }

    @SneakyThrows
    @Test
    @DisplayName("DELETE Task : Sunny Day")
    void deleteTask() {
        classUnderTest.deleteTask(TASK_ID);
        verify(taskCrudHandler).delete(TASK_ID);
    }

    @SneakyThrows
    @Test
    @DisplayName("(POST) Bulk Delete Tasks : Sunny Day")
    void BulkDeleteTasks() {
        classUnderTest.deleteTask(Set.of(TASK_ID));
        verify(taskCrudHandler).delete(Set.of(TASK_ID));
    }

}