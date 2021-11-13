package com.example.facebookapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDto {

    @NotBlank(message = "Nie podano nazwy uzytkownika")
    private String userName;

    @NotBlank(message = "Nie dodano zdjecia")
    private String statusImageURL;

    private LocalDateTime uploadTime;
}
