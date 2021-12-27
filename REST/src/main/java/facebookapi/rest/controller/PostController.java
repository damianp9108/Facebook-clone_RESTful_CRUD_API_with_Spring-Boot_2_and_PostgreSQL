package facebookapi.rest.controller;

import facebookapi.business.dto.NewPostDto;
import facebookapi.business.dto.PostDto;
import facebookapi.business.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto save(@RequestBody NewPostDto newPostDto) {
        return postService.savePost(newPostDto);
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
    public String delete(@PathVariable("postId") int postId) {
        return postService.deletePost(postId);

    }


    @DeleteMapping("/byUser/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteByUserId(@PathVariable("userId") int userId) {
        return postService.deleteUserPosts(userId);
    }
}
