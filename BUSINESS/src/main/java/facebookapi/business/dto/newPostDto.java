package facebookapi.business.dto;

import facebookapi.business.annotations.NotNullPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NotNullPost
public class NewPostDto {

    private int userId;
    private String description;
    private String postImgURL;
}
