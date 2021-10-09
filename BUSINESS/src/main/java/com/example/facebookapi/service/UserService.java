package com.example.facebookapi.service;

import com.example.facebookapi.entity.User;
import com.example.facebookapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User submitMetaDataOfUser(User user){

        Date date = new Date();
        long time = date.getTime();
        Timestamp dateTime = new Timestamp(time);

        user.setActive(false);
        user.setJoiningDate(dateTime);

        return userRepository.save(user);
    }

    public ArrayList<User> retrieveAllUserDetails(){
        return userRepository.findAll();
    }

    public User getUserData(String userID){
        return userRepository.findAllByUserID(userID);
    }

    public User changeUserActive(String userID){
        User userToChangeActive = getUserData(userID);
        boolean activity = userToChangeActive.isActive();
        userToChangeActive.setActive(!activity);

        return userRepository.save(userToChangeActive);

    }

}
