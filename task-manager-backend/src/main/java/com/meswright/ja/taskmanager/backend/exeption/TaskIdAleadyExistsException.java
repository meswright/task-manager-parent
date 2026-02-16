package com.meswright.ja.taskmanager.backend.exeption;

public class TaskIdAleadyExistsException extends TaskException {
    public TaskIdAleadyExistsException(String message) {
        super(message);
    }
}
