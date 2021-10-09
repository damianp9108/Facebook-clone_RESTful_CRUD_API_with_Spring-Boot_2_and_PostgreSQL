package com.example.facebookapi.repository;

import com.example.facebookapi.entity.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UserRepository extends CassandraRepository<User, UUID> {

    User findByUserID(UUID userID);

}
