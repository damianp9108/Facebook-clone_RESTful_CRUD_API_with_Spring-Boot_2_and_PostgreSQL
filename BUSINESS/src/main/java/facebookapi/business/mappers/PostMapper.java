package facebookapi.business.mappers;

import facebookapi.business.dto.NewPostDto;
import facebookapi.domain.entity.Post;
import facebookapi.business.dto.PostDto;
import facebookapi.domain.entity.User;
import facebookapi.domain.repository.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    protected UserRepository userRepository;

    @Mapping(source = "user", target = "userDto")
    public abstract PostDto toPostDto(Post post);

    @Mapping(source = "userId", target = "user", qualifiedByName = "userIdToUser")
    @Mapping(ignore = true, target = "dateTime")
    @Mapping(ignore = true, target = "likes")
    @Mapping(ignore = true, target = "comments")
    public abstract Post dtoToPost(NewPostDto newPostDto);

    @Named("userIdToUser")
    protected User setUser(int userId) {
        Optional<User> user = userRepository.findById(userId);

        return user.get();
    }

    @AfterMapping
    void setDateTime(@MappingTarget Post post) {
        LocalDateTime dateTime = LocalDateTime.now();
        post.setDateTime(dateTime);
    }

    @AfterMapping
    void setLikes(@MappingTarget Post post) {
        post.setLikes(0);
    }

    @AfterMapping
    void setComments(@MappingTarget Post post) {
        post.setComments(null);
    }

    public abstract List<PostDto> toPostsDto(List<Post> posts);

    public abstract List<Post> dtoToPosts(List<PostDto> postsDTO);
}
