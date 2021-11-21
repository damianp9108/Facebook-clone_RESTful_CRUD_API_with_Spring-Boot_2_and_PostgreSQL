package com.example.facebookapi.mappers;

import com.example.facebookapi.dto.CommentDto;
import com.example.facebookapi.dto.PostDto;
import com.example.facebookapi.dto.UserDto;
import com.example.facebookapi.entity.Comment;
import com.example.facebookapi.entity.Post;
import com.example.facebookapi.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;


@Mapper(componentModel = "spring")
public interface CommentMapper{
    @Mapping(source = "userDto", target = "user")
    @Mapping(source = "postDto", target = "post")
    Comment dtoToComment(CommentDto commentDto);

    @Mapping(source = "user", target = "userDto")
    @Mapping(source = "post", target = "postDto")
    CommentDto toCommentDto(Comment comment);

    List<CommentDto> toCommentDtos(List<Comment> comments);
    List<Comment> dtosToComments(List<CommentDto> commentDtos);


    //@Mapping(source = "posts", target = "postsDTO")
    //@Mapping(target = "postsDTO", ignore = true)
    UserDto toUserDto(User user);

    @Mapping(source = "user", target = "userDto")
    PostDto toPostDto(Post post);

    @Mapping(source = "userDto", target = "user")
    Post dtoToPost(PostDto postDto);

    @Mapping(source = "postsDTO", target = "posts")
    User dtoToUser(UserDto userDto);






    }

