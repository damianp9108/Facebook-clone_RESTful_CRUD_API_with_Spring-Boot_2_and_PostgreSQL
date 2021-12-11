package com.example.facebookapi.domain.repository;

import com.example.facebookapi.domain.entity.Comment;
import com.example.facebookapi.domain.entity.Post;
import com.example.facebookapi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByPost(Post post);

    List<Comment> findAllByUser(User user);

}
