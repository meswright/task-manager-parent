package com.meswright.ja.taskmanager.backend.mapper;

import com.meswright.dto.TaskDtoV1;
import com.meswright.ja.taskmanager.backend.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {

    TaskEntity toEntity(TaskDtoV1 dto);

    TaskDtoV1 toDto(TaskEntity entity);

    List<TaskEntity> toEntities(List<TaskDtoV1> dtos);

    List<TaskDtoV1> toDtos(List<TaskEntity> entities);

}
