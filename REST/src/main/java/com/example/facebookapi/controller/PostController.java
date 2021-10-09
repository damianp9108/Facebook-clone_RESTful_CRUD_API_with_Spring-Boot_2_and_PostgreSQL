package com.example.facebookapi.controller;

import com.example.facebookapi.entity.Post;
import com.example.facebookapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        postService.submitPostToDB(body);
        return ResponseEntity.ok("Successfully saved");
    }

    @GetMapping("/getPost")
    public ResponseEntity retrieveAllPost(){
        ArrayList<Post> result = postService.retrievePostFromDB();
        result.sort((e1, e2) -> e2.getDateTime().compareTo(e1.getDateTime()));
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{postId}")
    public ArrayList<Post> deleteParticularPost(@PathVariable("postId") UUID postID){
        ArrayList<Post> result = postService.deletePostFromDB(postID);
        return result;
    }

    @GetMapping("/userPosts/{userID}")
    public ArrayList<Post> getPostsOfUser (@PathVariable("userID") String userID){
        return postService.particularUserPosts(userID);
    }

    @DeleteMapping("/deleteUserPosts/{userID}")
    public ArrayList<Post> deleteByUserID (@PathVariable("userID") String userID){
        return postService.deleteUserPosts(userID);
    }
}