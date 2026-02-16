package com.meswright.ja.taskmanager.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

/**
 * Task POJO purely for persistence purposes.
 */
@Entity
@Data
@Table(name = "Task")
public class TaskEntity {

    @Id
    private UUID id;
    private String title;
    private String description;
    private String status;

}
