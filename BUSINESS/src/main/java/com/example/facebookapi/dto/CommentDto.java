package com.example.facebookapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private String userName;

    @NotNull
    private UUID postID;

    @NotBlank(message = "komentarz nie moze byc pusty")
    @Length(max = 300, message = "komentarz jest za dlugi")
    private String comment;

    private LocalDateTime time;
}
