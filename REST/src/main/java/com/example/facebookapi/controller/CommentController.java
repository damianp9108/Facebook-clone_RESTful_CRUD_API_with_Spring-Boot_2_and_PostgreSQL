package com.example.facebookapi.controller;

import com.example.facebookapi.entity.Comment;
import com.example.facebookapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/save")
    public Comment save(@RequestBody @Valid Comment comment){
        return commentService.saveComment(comment);
    }

    @GetMapping("/{postID}")
    public List<Comment> getByPostID(@PathVariable ("postID") UUID postID){
        return commentService.getCommentsByPostID(postID);
    }

    @GetMapping
    public List<Comment> get(){
        return commentService.getAllComments();
    }

    @DeleteMapping("/{commentID}")
    public List<Comment> delete(@PathVariable("commentID") UUID commentID){
        return commentService.deleteComment(commentID);
    }

    @GetMapping("/byUser/{userID}")
    public List<Comment> getByUserID (@PathVariable("userID") UUID userID){
        return commentService.getCommentsByUserID(userID);
    }

    @DeleteMapping("/byUser/{userID}")
    public List<Comment> deleteByUserID (@PathVariable("userID") UUID userID){
        return commentService.deleteCommentsByUserID(userID);
    }

}
