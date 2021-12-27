package facebookapi.rest.controller;

import facebookapi.business.dto.CommentDto;
import facebookapi.business.dto.NewCommentDto;
import facebookapi.business.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto save(@RequestBody @Valid NewCommentDto newComment) {
        return commentService.saveComment(newComment);
    }


    @GetMapping("/{postId}")
    public List<CommentDto> getByPostId(@PathVariable("postId") int postId) {
        return commentService.getCommentsByPostId(postId);
    }


    @GetMapping
    public List<CommentDto> get() {
        return commentService.getAllComments();
    }


    @GetMapping("/byUser/{userId}")
    public List<CommentDto> getByUserId(@PathVariable("userId") int userId) {
        return commentService.getCommentsByUserId(userId);
    }


    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable("commentId") int commentId) {
        return commentService.deleteComment(commentId);
    }


    @DeleteMapping("/byUser/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteByUserId(@PathVariable("userId") int userId) {
        return commentService.deleteCommentsByUserId(userId);
    }

}
