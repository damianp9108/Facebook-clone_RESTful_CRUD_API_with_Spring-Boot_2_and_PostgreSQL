package facebookapi.rest.controller;

import facebookapi.business.dto.PostDto;
import facebookapi.business.payload.request.NewPostRequest;
import facebookapi.business.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto save(@RequestBody @Valid NewPostRequest newPostRequest) {
        return postService.savePost(newPostRequest);
    }


    @GetMapping("/{userId}")
    public List<PostDto> getByUserId(@PathVariable("userId") int userId) {
        return postService.getUserPosts(userId);
    }


    @GetMapping
    public List<PostDto> get() {
        return postService.getPosts();
    }


    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deletePost(@PathVariable("postId") int postId) {
        return postService.deletePost(postId);

    }


    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteUserPosts() {
        return postService.deleteUserPosts();
    }
}
