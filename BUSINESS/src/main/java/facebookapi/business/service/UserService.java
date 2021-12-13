package facebookapi.business.service;

import facebookapi.business.dto.SignUpDto;
import facebookapi.business.dto.UserDto;
import facebookapi.domain.entity.User;
import facebookapi.business.exceptions.UserAlreadyExistsException;
import facebookapi.business.exceptions.UserNotExist;
import facebookapi.business.mappers.UserMapper;
import facebookapi.domain.repository.UserRepository;
import facebookapi.business.exceptions.LoginErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserDto saveUser(SignUpDto userDto) {
        Optional<User> userFromDB = userRepository.findByUserName(userDto.getUserName());
        if (userFromDB.isPresent()) {
            throw new UserAlreadyExistsException(userDto.getUserName());
        }

        LocalDateTime dateTime = LocalDateTime.now();
        User newUser = userMapper.dtoToUser(userDto);
        newUser.setActive(false);
        newUser.setJoiningDate(dateTime);

        var savedUser = userRepository.save(newUser);

        return userMapper.toUserDto(savedUser);
    }

    public List<UserDto> getAllUsersWithPosts() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserDtos(users);
    }

    public List<String> getUserNamesList() {
        List<String> userNamesList = userRepository.findAll().stream()
                .map(User::getUserName)
                .collect(Collectors.toList());
        return userNamesList;
    }

    public UserDto getUser(int userID) {
        Optional<User> userFromDB = userRepository.findById(userID);
        if (userFromDB.isEmpty()) {
            throw new UserNotExist(userID);
        }
        return userMapper.toUserDto(userFromDB.get());

    }

    public String changeActive(int userID) {
        Optional<User> userToChangeActive = userRepository.findById(userID);
        boolean activity = userToChangeActive.get().isActive();
        userToChangeActive.get().setActive(!activity);
        userRepository.save(userToChangeActive.get());
        return "active: " + userToChangeActive.get().isActive();

    }


    public UserDto login(SignUpDto userDto) {
        Optional<User> userFromDb = userRepository.findByUserName(userDto.getUserName());

        if (userFromDb.isEmpty() || wrongPassword(userFromDb.get(), userDto.getPassword())) {
            throw new LoginErrorException();
        }

        return userMapper.toUserDto(userFromDb.get());
    }

    public boolean wrongPassword(User user, String password) {
        return !user.getPassword().equals(password);
    }

    public List<UserDto> deleteUser(int userID) {
        Optional<User> userFromDB = userRepository.findById(userID);
        if (userFromDB.isEmpty()) {
            throw new UserNotExist(userID);
        }
        userRepository.deleteById(userID);

        return getAllUsersWithPosts();

    }

}
