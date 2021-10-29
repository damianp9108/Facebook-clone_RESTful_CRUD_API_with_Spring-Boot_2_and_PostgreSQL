package com.example.facebookapi.service;

import com.example.facebookapi.entity.Comment;
import com.example.facebookapi.entity.Post;
import com.example.facebookapi.entity.User;
import com.example.facebookapi.exceptions.PostException;
import com.example.facebookapi.exceptions.PostNotExist;
import com.example.facebookapi.exceptions.UserNotExist;
import com.example.facebookapi.repository.CommentRepository;
import com.example.facebookapi.repository.PostRepository;
import com.example.facebookapi.repository.UserRepository;
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
    private final UserRepository userRepository;


        public Post savePost (Post post) {


            if ((post.getDescription() == null || post.getDescription().isBlank()) &&
                (post.getPostImgURL() == null || post.getPostImgURL().isBlank())) {
            throw new PostException();
        }


        LocalDateTime time = LocalDateTime.now();
       // post.setUserName(checkedUser.getUserName());
        post.setPostID(UUID.randomUUID());
        post.setLikes(0);
        post.setDateTime(time);

        return postRepository.save(post);

    }


    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public List<Post> deletePost(UUID postID){
        Optional<Post> post = postRepository.findById(postID);
        if (post.isEmpty()){
            throw new PostNotExist(postID);
        }

        List<Comment> commentsToDelete = commentService.getCommentsByPostID(postID);
        commentRepository.deleteAll(commentsToDelete);
        postRepository.deleteById(postID);

        return getPosts();
    }

    public List<Post> getUserPosts (UUID userID){
        Optional<User> userFromDB = userRepository.findByUserID(userID);
        if (userFromDB.isEmpty()) {
            throw new UserNotExist(userID);
        }

        return postRepository.findAllByUserID(userID);
    }

    public List<Post> deleteUserPosts (UUID userID){
      //  checkUser(userID);
        List<Post> toDelete = postRepository.findAllByUserID(userID);

        toDelete.forEach(post -> {
            List<Comment> commentsToDelete = commentService.getCommentsByPostID(post.getPostID());
            commentRepository.deleteAll(commentsToDelete);
        });

        postRepository.deleteAll(toDelete);

        return getPosts();
    }
}
