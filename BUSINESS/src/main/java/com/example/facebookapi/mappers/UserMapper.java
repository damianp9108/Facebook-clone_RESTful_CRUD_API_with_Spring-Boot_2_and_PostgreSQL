package com.example.facebookapi.mappers;

import com.example.facebookapi.dto.PostDto;
import com.example.facebookapi.dto.SignUpDto;
import com.example.facebookapi.dto.UserDto;
import com.example.facebookapi.entity.Post;
import com.example.facebookapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "posts", target = "postsDTO")
    UserDto toUserDto(User user);

    List<UserDto> toUserDtos(List<User> users);

    User dtoToUser(SignUpDto userDto);

    List<PostDto> toPostDtos(List<Post> posts);
    List<Post> dtosToPosts(List<PostDto> postsDTO);
}
