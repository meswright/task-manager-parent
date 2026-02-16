package com.meswright.ja.taskmanager.backend.exeption;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TaskException extends Exception {
    public TaskException(String message) {
        super(message);
    }

}
