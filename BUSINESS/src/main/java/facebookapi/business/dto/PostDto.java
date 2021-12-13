package facebookapi.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
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
