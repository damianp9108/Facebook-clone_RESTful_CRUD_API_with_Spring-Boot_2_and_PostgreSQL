package facebookapi.business.dto;

import facebookapi.business.annotations.Password;
import facebookapi.business.annotations.UserName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDto {

    @UserName
    private String userName;

    @Password
    private String password;

    private String userImage;
}
