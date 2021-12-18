package facebookapi.business.dto;

import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDto {
    @Pattern(regexp = "^((?!.*[\\s]).{4,14})", message = "nazwa uzytkownia musi zawierac 4-14 znakow, spacja niedozwolona")
    private String userName;

    @Pattern(regexp = "^((?!.*[\\s]).{5,16})", message = "Haslo musi zawierac 5-16 znakow, spacja niedozwolona")
    private String password;

    private String userImage;
}
