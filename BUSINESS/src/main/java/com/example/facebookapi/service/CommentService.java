package com.example.facebookapi.service;

import com.example.facebookapi.entity.Comment;
import com.example.facebookapi.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment saveComment(Comment comment){

        LocalDateTime dateTime = LocalDateTime.now();

        comment.setCommentID(UUID.randomUUID());
        comment.setTime(dateTime);

        return commentRepository.save(comment);
    }

    public List<Comment> getAllComment(UUID postID){
        return commentRepository.findAllByPostID(postID);
    }

    public List<Comment> getAllUsersComments(){
        return commentRepository.findAll();
    }

    public List<Comment> deleteComment(UUID commentID){
        commentRepository.deleteById(commentID);
        return getAllUsersComments();
    }

    public List<Comment> getUserComments (UUID userID){
        return commentRepository.findAllByUserID(userID);
    }

    public List<Comment> deleteUserComments (UUID userID){
        List<Comment> toDelete = commentRepository.findAllByUserID(userID);
        commentRepository.deleteAll(toDelete);
        return getAllUsersComments();
    }
}
