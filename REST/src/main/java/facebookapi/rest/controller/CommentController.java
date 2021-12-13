package facebookapi.rest.controller;

import facebookapi.business.dto.CommentDto;
import facebookapi.business.dto.CommentDtoRequestBody;
import facebookapi.business.service.CommentService;
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

    @GetMapping("/{postId}")
    public List<CommentDto> getByPostId(@PathVariable ("postId") int postId){
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping
    public List<CommentDto> get(){
        return commentService.getAllComments();
    }

    @DeleteMapping("/{commentId}")
    public List<CommentDto> delete(@PathVariable("commentId") int commentId){
        return commentService.deleteComment(commentId);
    }

    @GetMapping("/byUser/{userId}")
    public List<CommentDto> getByUserId (@PathVariable("userId") int userId){
        return commentService.getCommentsByUserId(userId);
    }

    @DeleteMapping("/byUser/{userId}")
    public List<CommentDto> deleteByUserId (@PathVariable("userId") int userId){
        return commentService.deleteCommentsByUserId(userId);
    }

}
