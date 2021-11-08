package com.example.facebookapi.controller;

import com.example.facebookapi.dto.PostDto;
import com.example.facebookapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;


    @PostMapping ("/save")
    public PostDto save(@RequestBody @Valid PostDto postDto) {
        return postService.savePost(postDto);
    }

    @GetMapping
    public List<PostDto> get(){
        List<PostDto> result = postService.getPosts();
        result.sort((e1, e2) -> e2.getDateTime().compareTo(e1.getDateTime()));
        return result;
    }

    @DeleteMapping("/{postId}")
    public List<PostDto> delete(@PathVariable("postId") UUID postID){
        return postService.deletePost(postID);

    }

    @GetMapping("/{userID}")
    public List<PostDto> getByUserID (@PathVariable("userID") UUID userID){
        return postService.getUserPosts(userID);
    }

    @DeleteMapping("/byUser/{userID}")
    public List<PostDto> deleteByUserID (@PathVariable("userID") UUID userID){
        return postService.deleteUserPosts(userID);
    }
}
