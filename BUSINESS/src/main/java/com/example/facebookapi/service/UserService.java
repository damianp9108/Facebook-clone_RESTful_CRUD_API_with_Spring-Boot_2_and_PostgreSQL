package com.example.facebookapi.service;

import com.example.facebookapi.entity.User;
import com.example.facebookapi.exceptions.LoginErrorException;
import com.example.facebookapi.exceptions.UserAlreadyExistsException;
import com.example.facebookapi.exceptions.UserNotExist;
import com.example.facebookapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public User saveUser(User user)  {
        Optional<User> userFromDB = userRepository.findByUserName(user.getUserName());
        if (userFromDB.isPresent()) {
            throw new UserAlreadyExistsException(user.getUserName());
        }

            LocalDateTime dateTime = LocalDateTime.now();
            user.setUserID(UUID.randomUUID());
            user.setActive(false);
            user.setJoiningDate(dateTime);

            return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(UUID userID) {
        Optional<User> userFromDB = userRepository.findByUserID(userID);
        if (userFromDB.isEmpty()) {
            throw new UserNotExist(userID);
        }
        return userFromDB.get();

    }

    public User changeActive(UUID userID) {
        User userToChangeActive = getUser(userID);
        boolean activity = userToChangeActive.isActive();
        userToChangeActive.setActive(!activity);

        return userRepository.save(userToChangeActive);

    }


    public User login(String userName, String password){
        Optional<User> userFromDb = userRepository.findByUserName(userName);

        if(userFromDb.isEmpty() || wrongPassword(userFromDb.get(), password)){
            throw new LoginErrorException();
        }

        return userFromDb.get();
    }

    public boolean wrongPassword(User user, String password) {
        return !user.getPassword().equals(password);
    }

}
