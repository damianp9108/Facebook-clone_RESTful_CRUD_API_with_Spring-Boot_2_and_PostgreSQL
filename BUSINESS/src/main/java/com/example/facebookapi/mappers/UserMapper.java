package com.example.facebookapi.mappers;

import com.example.facebookapi.dto.UserDto;
import com.example.facebookapi.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
    List<UserDto> toUserDtos(List<User> users);
}
