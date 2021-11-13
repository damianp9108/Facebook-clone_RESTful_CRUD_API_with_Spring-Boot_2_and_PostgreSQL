package com.example.facebookapi.controller;

import com.example.facebookapi.dto.CommentDto;
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
    public CommentDto save(@RequestBody @Valid CommentDto commentDto){
        return commentService.saveComment(commentDto);
    }

    @GetMapping("/{postID}")
    public List<CommentDto> getByPostID(@PathVariable ("postID") UUID postID){
        return commentService.getCommentsByPostID(postID);
    }

    @GetMapping
    public List<CommentDto> get(){
        return commentService.getAllComments();
    }

    @DeleteMapping("/{commentID}")
    public List<CommentDto> delete(@PathVariable("commentID") UUID commentID){
        return commentService.deleteComment(commentID);
    }

    @GetMapping("/byUser/{userID}")
    public List<CommentDto> getByUserID (@PathVariable("userID") UUID userID){
        return commentService.getCommentsByUserID(userID);
    }

    @DeleteMapping("/byUser/{userID}")
    public List<CommentDto> deleteByUserID (@PathVariable("userID") UUID userID){
        return commentService.deleteCommentsByUserID(userID);
    }

}
