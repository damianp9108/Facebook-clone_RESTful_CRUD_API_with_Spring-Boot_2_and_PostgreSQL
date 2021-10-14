package com.example.facebookapi.service;

import com.example.facebookapi.entity.Comment;
import com.example.facebookapi.entity.Post;
import com.example.facebookapi.repository.CommentRepository;
import com.example.facebookapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {


    private final PostRepository postRepository;
    private final CommentService commentService;
    private final CommentRepository commentRepository;


    public Post savePost(Post post){

        LocalDateTime time = LocalDateTime.now();

        post.setPostID(UUID.randomUUID());
        post.setLikes(0);
        post.setDateTime(time);

        return postRepository.save(post);
    }

    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public List<Post> deletePost(UUID postID){

        List<Comment> commentsToDelete = commentService.getCommentsByPostID(postID);
        commentRepository.deleteAll(commentsToDelete);
        postRepository.deleteById(postID);

        return getPosts();
    }

    public List<Post> getUserPosts (UUID userID){
        return postRepository.findAllByUserID(userID);
    }

    public List<Post> deleteUserPosts (UUID userID){
        List<Post> toDelete = postRepository.findAllByUserID(userID);

        toDelete.forEach(post -> {
            List<Comment> commentsToDelete = commentService.getCommentsByPostID(post.getPostID());
            commentRepository.deleteAll(commentsToDelete);
        });

        postRepository.deleteAll(toDelete);

        return getPosts();
    }
}
