package facebookapi.business.mappers;

import facebookapi.business.dto.UserDto;
import facebookapi.business.exceptions.RoleNotFoundException;
import facebookapi.business.payload.request.SignupRequest;
import facebookapi.domain.ERole;
import facebookapi.domain.entity.Role;
import facebookapi.domain.entity.User;
import facebookapi.domain.repository.RoleRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected PasswordEncoder encoder;


    @Mapping(source = "roles", target = "roles", qualifiedByName = "RoleSet_To_StringList")
    @Mapping(source = "posts", target = "postsDTO")
    public abstract UserDto toUserDto(User user);


    @Named("RoleSet_To_StringList")
    protected List<String> setRoles(Set<Role> roles) {

        List<String> rolesList = roles.stream()
                .map(item -> item.getName().toString())
                .collect(Collectors.toList());

        return rolesList;
    }

    public abstract List<UserDto> toUsersDto(List<User> users);


    @Mapping(ignore = true, target = "joiningDate")
    @Mapping(ignore = true, target = "roles")
    @Mapping(source = "password", target = "password", qualifiedByName = "passwordEncoder")
    public abstract User dtoToUser(SignupRequest signupRequest);


    @AfterMapping
    void setRoles(@MappingTarget User user) {
        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RoleNotFoundException());
        roles.add(userRole);

        user.setRoles(roles);

    }

    @AfterMapping
    void setJoiningDate(@MappingTarget User user) {
        LocalDateTime time = LocalDateTime.now();
        user.setJoiningDate(time);
    }


    @Named("passwordEncoder")
    protected String encodePassword(String password) {
        String encodedPassword = encoder.encode(password);

        return encodedPassword;
    }


}
