package com.meswright.dto;

import java.util.UUID;

/**
 * The Data Transfer Object of a Task.
 * Is part of the API for the Backend.
 *
 * @param id          Unique identifier of the task
 * @param title       Task's title
 * @param description Task's detail
 * @param status      The status the task is in.
 */
public record TaskDtoV1(UUID id, String title, String description, String status) {
}
