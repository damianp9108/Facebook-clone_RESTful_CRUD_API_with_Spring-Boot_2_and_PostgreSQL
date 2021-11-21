package com.example.facebookapi.controller;

import com.example.facebookapi.dto.CommentDto;
import com.example.facebookapi.dto.CommentDtoRequestBody;
import com.example.facebookapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;


    @PostMapping
    public CommentDto save(@RequestBody @Valid CommentDtoRequestBody newComment){
        return commentService.saveComment(newComment);
    }

    @GetMapping("/{postID}")
    public List<CommentDto> getByPostID(@PathVariable ("postID") int postID){
        return commentService.getCommentsByPostID(postID);
    }

    @GetMapping
    public List<CommentDto> get(){
        return commentService.getAllComments();
    }

    @DeleteMapping("/{commentID}")
    public List<CommentDto> delete(@PathVariable("commentID") int commentID){
        return commentService.deleteComment(commentID);
    }

    @GetMapping("/byUser/{userID}")
    public List<CommentDto> getByUserID (@PathVariable("userID") int userID){
        return commentService.getCommentsByUserID(userID);
    }

    @DeleteMapping("/byUser/{userID}")
    public List<CommentDto> deleteByUserID (@PathVariable("userID") int userID){
        return commentService.deleteCommentsByUserID(userID);
    }

}
