package com.example.facebookapi.mappers;

import com.example.facebookapi.dto.CommentDto;
import com.example.facebookapi.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment dtoToComment(CommentDto commentDto);
    CommentDto toCommentDto(Comment comment);
}
