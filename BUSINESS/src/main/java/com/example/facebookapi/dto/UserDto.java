package com.example.facebookapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull(message = "Prosze podac nazwe uzytkownika")
    @Pattern(regexp = "^((?!.*[\\s]).{4,14})", message = "nazwa uzytkownia musi zawierac 4-14 znakow, spacja niedozwolona")
    private String userName;
    private String userImage;

    @JsonIgnoreProperties("userDto")
    private List<PostDto> postsDTO;
}
