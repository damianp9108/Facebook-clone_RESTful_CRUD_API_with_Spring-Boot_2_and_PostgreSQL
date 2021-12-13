package facebookapi.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    @JsonIgnoreProperties("postsDTO")
    private UserDto userDto;

    public String description;
    public String postImgURL;
    private int likes;
    private LocalDateTime dateTime;
}
