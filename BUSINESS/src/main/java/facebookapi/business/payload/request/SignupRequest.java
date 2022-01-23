package facebookapi.business.payload.request;

import facebookapi.business.annotations.Password;
import facebookapi.business.annotations.UserName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class SignupRequest {

    @UserName
    private String userName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

   // private Set<String> role;

    @Password
    private String password;

    private String userImageURL;



}
