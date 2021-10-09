package com.example.facebookapi.controller;

import com.example.facebookapi.entity.Comment;
import com.example.facebookapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
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
    public ArrayList<Comment> getAllComments(){
        return commentService.getAllUsersComments();
    }

    @DeleteMapping("/{commentID}")
    public ArrayList<Comment> deleteByCommentID(@PathVariable("commentID") UUID commentID){
        return commentService.deleteComment(commentID);
    }

    @GetMapping("/userComments/{userID}")
    public List<Comment> getCommentsOfUser (@PathVariable("userID") String userID){
        return commentService.particularUserComments(userID);
    }

    @DeleteMapping("/{userID}")
    public ArrayList<Comment> deleteByUserID (@PathVariable("userID") String userID){
        return commentService.deleteUserComments(userID);
    }

}
