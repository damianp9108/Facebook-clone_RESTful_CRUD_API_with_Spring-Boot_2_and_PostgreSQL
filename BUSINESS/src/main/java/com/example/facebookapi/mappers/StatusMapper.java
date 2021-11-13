package com.example.facebookapi.mappers;

import com.example.facebookapi.dto.StatusDto;
import com.example.facebookapi.entity.Status;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatusMapper {
    StatusDto toStatusDto(Status status);
    Status dtoToStatus(StatusDto statusDto);
    List<StatusDto> toStatusDtos(List<Status> statuses);
}
