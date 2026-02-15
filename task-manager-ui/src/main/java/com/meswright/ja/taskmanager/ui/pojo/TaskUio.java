package com.meswright.ja.taskmanager.ui.pojo;

import lombok.Data;

import java.util.UUID;

@Data
public class TaskUio {
    private UUID id;
    private String title;
    private String description;
    private String status;
}
