package com.example.facebookapi.controller;

import com.example.facebookapi.entity.Post;
import com.example.facebookapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping ("/save")
    public ResponseEntity add(@RequestBody Post body) {
        postService.savePost(body);
        return ResponseEntity.ok("Successfully saved");
    }

    @GetMapping("/getPost")
    public ResponseEntity retrieveAllPost(){
        List<Post> result = postService.getPosts();
        result.sort((e1, e2) -> e2.getDateTime().compareTo(e1.getDateTime()));
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{postId}")
    public List<Post> deleteParticularPost(@PathVariable("postId") UUID postID){
        List<Post> result = postService.deletePost(postID);
        return result;
    }

    @GetMapping("/userPosts/{userID}")
    public List<Post> getPostsOfUser (@PathVariable("userID") UUID userID){
        return postService.getUserPosts(userID);
    }

    @DeleteMapping("/deleteUserPosts/{userID}")
    public List<Post> deleteByUserID (@PathVariable("userID") UUID userID){
        return postService.deleteUserPosts(userID);
    }
}
