package facebookapi.business.mappers;

import facebookapi.business.dto.CommentDto;
import facebookapi.business.dto.NewCommentDto;
import facebookapi.business.dto.UserDto;
import facebookapi.domain.entity.Comment;
import facebookapi.domain.entity.Post;
import facebookapi.domain.entity.User;
import facebookapi.domain.repository.PostRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Mapper(componentModel = "spring", uses = PostMapper.class)
public abstract class CommentMapper {

    @Autowired
    protected PostRepository postRepository;

    @Mapping(source = "userId", target = "user", qualifiedByName = "userIdToUser")
    @Mapping(source = "postId", target = "post", qualifiedByName = "postIdToPost")
    @Mapping(ignore = true, target = "time")
    public abstract Comment dtoToComment(NewCommentDto newCommentDto);

    @AfterMapping
    void setTime(@MappingTarget Comment comment) {
        LocalDateTime time = LocalDateTime.now();
        comment.setTime(time);
    }


    @Named("postIdToPost")
    protected Post setPost(int postId) {
        Optional<Post> post = postRepository.findById(postId);

        return post.get();
    }


    @Mapping(source = "user", target = "userDto")
    @Mapping(source = "post", target = "postDto")
    public abstract CommentDto toCommentDto(Comment comment);

    public abstract List<CommentDto> toCommentsDto(List<Comment> comments);

    public abstract UserDto userToUserDto(User user);


}

