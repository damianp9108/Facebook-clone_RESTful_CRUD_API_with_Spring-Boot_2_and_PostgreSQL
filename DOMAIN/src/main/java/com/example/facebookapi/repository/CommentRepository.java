package com.example.facebookapi.repository;

import com.example.facebookapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByPost(int postID);
    List<Comment> findAllByUser (int userID);

}
