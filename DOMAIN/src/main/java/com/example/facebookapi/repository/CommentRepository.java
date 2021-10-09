package com.example.facebookapi.repository;

import com.example.facebookapi.entity.Comment;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends CassandraRepository<Comment, UUID> {


    List<Comment> findAllByPostID(UUID postID);


    List<Comment> findAllByUserID (UUID userID);

}
