package com.meswright.ja.taskmanager.ui.mapper;

import com.meswright.dto.TaskDtoV1;
import com.meswright.ja.taskmanager.ui.pojo.TaskUio;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {

    TaskUio toUio(TaskDtoV1 dto);

    TaskDtoV1 toDto(TaskUio uio);

    List<TaskUio> toUios(List<TaskDtoV1> dtos);

    List<TaskDtoV1> toDtos(List<TaskUio> uios);

}
