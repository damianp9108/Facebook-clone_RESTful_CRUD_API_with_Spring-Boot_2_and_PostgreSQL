package com.example.facebookapi.business.mappers;

import com.example.facebookapi.domain.entity.Post;
import com.example.facebookapi.business.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "user", target = "userDto")
    PostDto toPostDto(Post post);

    @Mapping(source = "userDto", target = "user")
    Post dtoToPost(PostDto postDto);

    List<PostDto> toPostDtos(List<Post> posts);
    List<Post> dtosToPosts(List<PostDto> postsDTO );
}
