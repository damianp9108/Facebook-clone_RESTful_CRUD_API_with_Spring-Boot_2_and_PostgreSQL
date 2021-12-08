package com.example.facebookapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private UserDto userDto;
    private PostDto postDto;
    private String comment;
    private LocalDateTime time;

}
