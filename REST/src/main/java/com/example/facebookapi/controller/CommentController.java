package com.example.facebookapi.controller;

import com.example.facebookapi.entity.Comment;
import com.example.facebookapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/save")
    public Comment saveComment(@RequestBody Comment comment){
        return commentService.saveComment(comment);
    }

    @GetMapping("/getAllComments/{postID}")
    public List<Comment> getUserComments(@PathVariable("postID") UUID postID){
        return commentService.getAllComment(postID);
    }

    @GetMapping
    public List<Comment> getAllComments(){
        return commentService.getAllUsersComments();
    }

    @DeleteMapping("/{commentID}")
    public List<Comment> deleteByCommentID(@PathVariable("commentID") UUID commentID){
        return commentService.deleteComment(commentID);
    }

    @GetMapping("/userComments/{userID}")
    public List<Comment> get (@PathVariable("userID") UUID userID){
        return commentService.getUserComments(userID);
    }

    @DeleteMapping("/{userID}")
    public List<Comment> deleteByUserID (@PathVariable("userID") UUID userID){
        return commentService.deleteUserComments(userID);
    }

}
