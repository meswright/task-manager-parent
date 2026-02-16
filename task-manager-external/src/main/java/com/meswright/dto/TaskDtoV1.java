package com.meswright.dto;

import java.util.UUID;

public record TaskDtoV1(UUID id, String title, String description, String status) {
}
