package facebookapi.rest.controller;

import facebookapi.business.dto.UserDto;
import facebookapi.business.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;


    @PutMapping("/role")
    public UserDto setRole(@RequestParam("userId") int userId, @RequestParam("roleName") String roleName){
        return adminService.setRole(userId, roleName);
    }

    @DeleteMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteUser(@PathVariable("userId") int userId){
        return adminService.deleteUser(userId);
    }

    @DeleteMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deletePost(@PathVariable("postId") int postId){
        return adminService.deletePost(postId);
    }

    @DeleteMapping("/post/byUser/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteUserPosts(@PathVariable("userId") int userId){
        return adminService.deleteUserPosts(userId);
    }

    @DeleteMapping("/comment/{commentId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteComment(@PathVariable("commentId") int commentId){
        return adminService.deleteComment(commentId);
    }

    @DeleteMapping("/comment/byUser/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteUserComments(@PathVariable("userId") int userId){
        return adminService.deleteUserComments(userId);
    }


}
