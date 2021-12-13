package facebookapi.business.mappers;

import facebookapi.business.dto.CommentDto;
import facebookapi.business.dto.UserDto;
import facebookapi.domain.entity.Comment;
import facebookapi.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;


@Mapper(componentModel = "spring", uses = PostMapper.class)
public interface CommentMapper{
    @Mapping(source = "userDto", target = "user")
    @Mapping(source = "postDto", target = "post")
    Comment dtoToComment(CommentDto commentDto);

    @Mapping(source = "user", target = "userDto")
    @Mapping(source = "post", target = "postDto")
    CommentDto toCommentDto(Comment comment);

    List<CommentDto> toCommentDtos(List<Comment> comments);
    List<Comment> dtosToComments(List<CommentDto> commentDtos);
    UserDto userToUserDto(User user);








    }

