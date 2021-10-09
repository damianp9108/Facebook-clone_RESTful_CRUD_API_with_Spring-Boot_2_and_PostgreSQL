package com.example.facebookapi.repository;

import com.example.facebookapi.entity.Post;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends CassandraRepository<Post, UUID> {

    @AllowFiltering
    List<Post> findAllByUserID (UUID userID);

}
