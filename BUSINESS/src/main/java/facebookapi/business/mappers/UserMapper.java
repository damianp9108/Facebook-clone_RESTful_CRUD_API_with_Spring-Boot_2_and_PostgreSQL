package facebookapi.business.mappers;

import facebookapi.domain.entity.User;
import facebookapi.business.dto.NewUserDto;
import facebookapi.business.dto.UserDto;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", uses = PostMapper.class)
public abstract class UserMapper {

    @Mapping(source = "posts", target = "postsDTO")
    public abstract UserDto toUserDto(User user);

    public abstract List<UserDto> toUsersDto(List<User> users);

    @Mapping(ignore = true, target = "active")
    @Mapping(ignore = true, target = "joiningDate")
    public abstract User dtoToUser(NewUserDto userDto);

    @AfterMapping
    void setActiveForUser (@MappingTarget User user){
        user.setActive(false);
    }

    @AfterMapping
    void setJoiningDateForUser (@MappingTarget User user){
        LocalDateTime time = LocalDateTime.now();
        user.setJoiningDate(time);
    }

    @Mapping(source = "postsDTO", target = "posts")
    public abstract User dtoToUser(UserDto userDto);

}
