package facebookapi.business.payload.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewCommentRequest {

    private int postId;

    @NotBlank(message = "Komentarz nie moze byc pusty")
    @Length(max = 300, message = "Komentarz jest za dlugi")
    private String comment;
}
