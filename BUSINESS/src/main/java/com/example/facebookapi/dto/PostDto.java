package com.example.facebookapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    @NotNull(message = "Prosze podac nazwe uzytkownika")
    private String userName;
    private String imageURL;
    public String description;
    public String postImgURL;
    private int likes;
    private LocalDateTime dateTime;
}
