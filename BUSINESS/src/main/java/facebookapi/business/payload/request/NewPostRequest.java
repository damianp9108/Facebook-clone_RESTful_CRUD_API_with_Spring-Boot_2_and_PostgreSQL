package facebookapi.business.payload.request;

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
public class NewPostRequest {

    private String description;
    private String postImgURL;
}
