package com.example.facebookapi.mappers;

import com.example.facebookapi.dto.CommentDto;
import com.example.facebookapi.entity.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment dtoToComment(CommentDto commentDto);
    CommentDto toCommentDto(Comment comment);
    List<CommentDto> toCommentDtos(List<Comment> comments);
    List<Comment> dtosToComments(List<CommentDto> commentDtos);
}
