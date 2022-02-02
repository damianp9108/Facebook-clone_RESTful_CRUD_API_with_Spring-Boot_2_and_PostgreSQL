package facebookapi.rest.controller;

import facebookapi.business.dto.CommentDto;
import facebookapi.business.payload.request.NewCommentRequest;
import facebookapi.business.service.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
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
    @ApiOperation(value = "", authorizations = { @Authorization(value = "JWT") })
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto save(@RequestBody @Valid NewCommentRequest newComment) {
        return commentService.saveComment(newComment);
    }


    @GetMapping("/{postId}")
    @ApiOperation(value = "", authorizations = { @Authorization(value = "JWT") })
    public List<CommentDto> getByPostId(@PathVariable("postId") int postId) {
        return commentService.getCommentsByPostId(postId);
    }


    @GetMapping
    @ApiOperation(value = "", authorizations = { @Authorization(value = "JWT") })
    public List<CommentDto> get() {
        return commentService.getAllComments();
    }


    @GetMapping("/byUser/{userId}")
    @ApiOperation(value = "", authorizations = { @Authorization(value = "JWT") })
    public List<CommentDto> getByUserId(@PathVariable("userId") int userId) {
        return commentService.getCommentsByUserId(userId);
    }


    @DeleteMapping("/{commentId}")
    @ApiOperation(value = "", authorizations = { @Authorization(value = "JWT") })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable("commentId") int commentId) {
        return commentService.deleteComment(commentId);
    }


    @DeleteMapping
    @ApiOperation(value = "", authorizations = { @Authorization(value = "JWT") })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteUserComments() {
        return commentService.deleteUserComments();
    }

}
