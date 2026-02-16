package com.meswright.ja.taskmanager.backend.exeption;

public class TaskNotFoundException extends TaskException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
