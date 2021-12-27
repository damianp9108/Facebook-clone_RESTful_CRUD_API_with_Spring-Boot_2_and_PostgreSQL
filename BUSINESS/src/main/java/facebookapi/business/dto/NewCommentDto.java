package facebookapi.business.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewCommentDto {

    private int userId;
    private int postId;

    @NotBlank(message = "Komentarz nie moze byc pusty")
    @Length(max = 300, message = "Komentarz jest za dlugi")
    private String comment;
}
