package com.example.facebookapi.service;

import com.example.facebookapi.entity.Comment;
import com.example.facebookapi.entity.Post;
import com.example.facebookapi.entity.User;
import com.example.facebookapi.exceptions.CommentNotExist;
import com.example.facebookapi.exceptions.PostNotExist;
import com.example.facebookapi.exceptions.UserNotExist;
import com.example.facebookapi.repository.CommentRepository;
import com.example.facebookapi.repository.PostRepository;
import com.example.facebookapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserService userService;

    public void checkPost(UUID postID){
        Optional<Post> post = postRepository.findById(postID);
        if (post.isEmpty()){
            throw new PostNotExist(postID);
        }
    }

    public void checkComment(UUID commentID){
        Optional<Comment> comment = commentRepository.findById(commentID);
        if (comment.isEmpty()){
            throw new CommentNotExist(commentID);
        }
    }

    public Comment saveComment(Comment comment){
        User checkedUser = userService.getUser(comment.getUserID());
        checkPost(comment.getPostID());

        LocalDateTime dateTime = LocalDateTime.now();
        comment.setUserName(checkedUser.getUserName());
        comment.setCommentID(UUID.randomUUID());
        comment.setTime(dateTime);

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPostID(UUID postID){
        checkPost(postID);
        return commentRepository.findAllByPostID(postID);
    }

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public List<Comment> deleteComment(UUID commentID){
        checkComment(commentID);
        commentRepository.deleteById(commentID);
        return getAllComments();
    }

    public List<Comment> getCommentsByUserID (UUID userID){
        userService.getUser(userID);
        return commentRepository.findAllByUserID(userID);
    }

    public List<Comment> deleteCommentsByUserID (UUID userID){
        userService.getUser(userID);
        List<Comment> toDelete = commentRepository.findAllByUserID(userID);
        commentRepository.deleteAll(toDelete);
        return getAllComments();
    }
}
