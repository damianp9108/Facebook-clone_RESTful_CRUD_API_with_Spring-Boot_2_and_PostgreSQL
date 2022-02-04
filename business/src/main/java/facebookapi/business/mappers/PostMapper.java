package facebookapi.business.mappers;

import facebookapi.business.dto.PostDto;
import facebookapi.business.payload.request.NewPostRequest;
import facebookapi.business.service.UserService;
import facebookapi.domain.entity.Post;
import facebookapi.domain.entity.User;
import facebookapi.domain.repository.UserRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class )
public abstract class PostMapper {

    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected UserService userService;


    @Mapping(source = "user", target = "userDto")
    public abstract PostDto toPostDto(Post post);



    @Mapping(ignore = true, target = "user")
    @Mapping(ignore = true, target = "dateTime")
    @Mapping(ignore = true, target = "likes")
    @Mapping(ignore = true, target = "comments")
    public abstract Post toPost(NewPostRequest newPostRequest);

    @AfterMapping
    void setUser(@MappingTarget Post post) {
        int userId = userService.retrieveCurrentlyAuthenticatedUserId();
        User user = userRepository.findById(userId).get();
        post.setUser(user);
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


}
