package com.example.facebookapi.mappers;

import com.example.facebookapi.dto.PostDto;
import com.example.facebookapi.entity.Post;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDto toPostDto(Post post);
    Post dtoToPost(PostDto postDto);
    List<PostDto> toPostDtos(List<Post> posts);
    List<Post> dtosToPosts(List<PostDto> dtoPosts);
}
