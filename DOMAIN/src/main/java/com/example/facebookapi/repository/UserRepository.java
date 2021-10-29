package com.example.facebookapi.repository;

import com.example.facebookapi.entity.User;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CassandraRepository<User, UUID> {

    Optional<User> findByUserID(UUID userID);

    @AllowFiltering
    Optional<User> findByUserName(String userName);

}
