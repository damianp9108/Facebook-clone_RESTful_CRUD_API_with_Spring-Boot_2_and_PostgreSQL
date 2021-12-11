package com.example.facebookapi.business.mappers;

import com.example.facebookapi.domain.entity.User;
import com.example.facebookapi.business.dto.SignUpDto;
import com.example.facebookapi.business.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = PostMapper.class)
public interface UserMapper {
    @Mapping(source = "posts", target = "postsDTO")
    UserDto toUserDto(User user);

    List<UserDto> toUserDtos(List<User> users);

    User dtoToUser(SignUpDto userDto);

    @Mapping(source = "postsDTO", target = "posts" )
    User dtoToUser(UserDto userDto);

}
