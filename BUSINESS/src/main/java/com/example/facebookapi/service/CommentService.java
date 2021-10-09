package com.example.facebookapi.service;

import com.example.facebookapi.entity.Comment;
import com.example.facebookapi.repository.CommentRepository;
import com.example.facebookapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public Comment saveComment(Comment comment){

        Date date = new Date();
        long time = date.getTime();
        Timestamp dateTime = new Timestamp(time);

        comment.setCommentID(UUID.randomUUID());
        comment.setTimestamp(dateTime);

        return commentRepository.save(comment);
    }

    public List<Comment> getAllComment(UUID postID){
        return commentRepository.findAllByPostID(postID);
    }

    public ArrayList<Comment> getAllUsersComments(){
        return commentRepository.findAll();
    }

    public ArrayList<Comment> deleteComment(UUID commentID){
        commentRepository.deleteByCommentID(commentID);
        return getAllUsersComments();
    }

    public List<Comment> particularUserComments (String userID){
        return commentRepository.findAllByUserID(userID);
    }

    public ArrayList<Comment> deleteUserComments (String userID){
        ArrayList<Comment> toDelete = commentRepository.findAllByUserID(userID);
        commentRepository.deleteAll(toDelete);
        return getAllUsersComments();
    }
}
