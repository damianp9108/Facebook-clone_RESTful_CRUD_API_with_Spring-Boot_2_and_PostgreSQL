package com.example.facebookapi.repository;

import com.example.facebookapi.entity.Post;
import com.example.facebookapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);

}
