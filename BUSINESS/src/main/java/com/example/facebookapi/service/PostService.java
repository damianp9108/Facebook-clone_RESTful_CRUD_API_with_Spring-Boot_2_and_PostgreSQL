package com.example.facebookapi.service;

import com.example.facebookapi.entity.Comment;
import com.example.facebookapi.entity.Post;
import com.example.facebookapi.repository.CommentRepository;
import com.example.facebookapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentService commentService;
    @Autowired
    CommentRepository commentRepository;


    public void submitPostToDB(Post postData){

        Date date = new Date();
        long time = date.getTime();
        Timestamp dateTime = new Timestamp(time);

        postData.setPostID(UUID.randomUUID());
        postData.setLikes(0);
        postData.setDateTime(dateTime);

        postRepository.save(postData);
    }

    public ArrayList<Post> retrievePostFromDB(){
        return postRepository.findAll();
    }

    public ArrayList<Post> deletePostFromDB(UUID postID){
        postRepository.deleteById(postID);

        List<Comment> commentsToDelete = commentService.getAllComment(postID);
        commentRepository.deleteAll(commentsToDelete);

        ArrayList<Post> result = retrievePostFromDB();
        return result;
    }

    public ArrayList<Post> particularUserPosts (String userID){
        return postRepository.findAllByUserID(userID);
    }

    public ArrayList<Post> deleteUserPosts (String userID){
        ArrayList<Post> toDelete = postRepository.findAllByUserID(userID);

        toDelete.forEach(post -> {
            List<Comment> commentsToDelete = commentService.getAllComment(post.getPostID());
            commentRepository.deleteAll(commentsToDelete);
        });

        postRepository.deleteAll(toDelete);

        return retrievePostFromDB();
    }
}
