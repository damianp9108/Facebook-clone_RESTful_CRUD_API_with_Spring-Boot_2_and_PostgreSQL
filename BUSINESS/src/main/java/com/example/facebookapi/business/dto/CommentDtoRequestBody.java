package com.example.facebookapi.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDtoRequestBody {

    private int userID;
    private int postID;

    @NotBlank(message = "komentarz nie moze byc pusty")
    @Length(max = 300, message = "komentarz jest za dlugi")
    private String comment;
}
