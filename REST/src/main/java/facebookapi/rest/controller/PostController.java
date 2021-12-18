package facebookapi.rest.controller;

import facebookapi.business.dto.NewPostDto;
import facebookapi.business.dto.PostDto;
import facebookapi.business.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;


    @PostMapping
    public PostDto save(@RequestBody @Valid NewPostDto newPostDto) {
        return postService.savePost(newPostDto);
    }

    @GetMapping
    public List<PostDto> get(){
        List<PostDto> result = postService.getPosts();
        result.sort((e1, e2) -> e2.getDateTime().compareTo(e1.getDateTime()));
        return result;
    }

    @DeleteMapping("/{postId}")
    public String delete(@PathVariable("postId") int postId){
        return postService.deletePost(postId);

    }

    @GetMapping("/{userId}")
    public List<PostDto> getByUserId (@PathVariable("userId") int userId){
        return postService.getUserPosts(userId);
    }

    @DeleteMapping("/byUser/{userId}")
    public String deleteByUserId (@PathVariable("userId") int userId){
        return postService.deleteUserPosts(userId);
    }
}
