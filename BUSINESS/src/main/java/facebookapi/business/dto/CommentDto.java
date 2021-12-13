package facebookapi.business.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private UserDto userDto;
    private PostDto postDto;
    private String comment;
    private LocalDateTime time;

}
