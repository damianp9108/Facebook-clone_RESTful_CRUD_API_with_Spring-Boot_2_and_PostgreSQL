package com.example.facebookapi.repository;

import com.example.facebookapi.entity.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserRepository extends CassandraRepository<User, String> {

    User save(User user);
    ArrayList<User> findAll();
    User findAllByUserID(String userID);

}
