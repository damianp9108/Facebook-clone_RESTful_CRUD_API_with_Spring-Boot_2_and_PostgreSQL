package facebookapi.business.service;

import facebookapi.business.IdChecker;
import facebookapi.business.dto.NewUserDto;
import facebookapi.business.dto.UserDto;
import facebookapi.business.exceptions.LoginErrorException;
import facebookapi.business.exceptions.UserAlreadyExistException;
import facebookapi.business.exceptions.UsernameNotExistException;
import facebookapi.business.mappers.UserMapper;
import facebookapi.domain.entity.User;
import facebookapi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final IdChecker idChecker;

    public UserDto saveUser(NewUserDto userDto) {
        Optional<User> userFromDB = userRepository.findByUserName(userDto.getUserName());

        if (userFromDB.isPresent()) {
            throw new UserAlreadyExistException(userDto.getUserName());
        }

        User newUser = userMapper.dtoToUser(userDto);
        var savedUser = userRepository.save(newUser);

        return userMapper.toUserDto(savedUser);
    }

    public List<UserDto> getAllUsersWithPosts() {
        List<User> users = userRepository.findAll();

        return userMapper.toUsersDto(users);
    }

    public List<String> getUserNamesList() {
        List<String> userNamesList = userRepository.findAll().stream()
                .map(User::getUserName)
                .collect(Collectors.toList());

        return userNamesList;
    }

    public UserDto getUser(int userId) {
        User user = idChecker.isUserAvailable(userId);

        return userMapper.toUserDto(user);

    }

    public String changeActive(int userId) {
        User user = idChecker.isUserAvailable(userId);
        boolean activity = user.isActive();
        user.setActive(!activity);
        userRepository.save(user);

        return "active: " + user.isActive();

    }

    public UserDto login(NewUserDto userDto) {
        User userFromDb = userRepository.findByUserName(userDto.getUserName())
                .orElseThrow(() -> new UsernameNotExistException(userDto.getUserName()));

        if (wrongPassword(userFromDb, userDto.getPassword())) {
            throw new LoginErrorException();
        }

        return userMapper.toUserDto(userFromDb);
    }

    public boolean wrongPassword(User user, String password) {
        return !user.getPassword().equals(password);
    }

    public String deleteUser(int userId) {
        idChecker.isUserAvailable(userId);
        userRepository.deleteById(userId);

        return "Uzytkownik o numerze Id: " + userId + " zostal pomyslnie usuniety";

    }

}
