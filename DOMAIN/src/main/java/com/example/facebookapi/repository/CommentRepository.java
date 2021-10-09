package com.example.facebookapi.repository;

import com.example.facebookapi.entity.Comment;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface CommentRepository extends CassandraRepository<Comment, UUID> {

    ArrayList<Comment> findAll();

    @AllowFiltering
    ArrayList<Comment> findAllByPostID(UUID postID);

    ArrayList<Comment> deleteByCommentID(UUID commentID);

    @AllowFiltering
    ArrayList<Comment> findAllByUserID (String userID);


    void deleteAll();

}
