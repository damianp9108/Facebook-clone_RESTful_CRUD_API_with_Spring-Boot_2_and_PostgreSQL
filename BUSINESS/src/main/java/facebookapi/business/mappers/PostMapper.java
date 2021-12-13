package facebookapi.business.mappers;

import facebookapi.domain.entity.Post;
import facebookapi.business.dto.PostDto;
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
