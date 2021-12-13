package facebookapi.business.mappers;

import facebookapi.domain.entity.User;
import facebookapi.business.dto.SignUpDto;
import facebookapi.business.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = PostMapper.class)
public interface UserMapper {
    @Mapping(source = "posts", target = "postsDTO")
    UserDto toUserDto(User user);

    List<UserDto> toUserDtos(List<User> users);

    User dtoToUser(SignUpDto userDto);

    @Mapping(source = "postsDTO", target = "posts" )
    User dtoToUser(UserDto userDto);

}
