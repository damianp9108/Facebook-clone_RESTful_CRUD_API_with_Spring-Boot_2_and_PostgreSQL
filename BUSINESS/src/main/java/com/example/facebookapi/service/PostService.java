package com.example.facebookapi.service;

import com.example.facebookapi.dto.PostDto;
import com.example.facebookapi.entity.Comment;
import com.example.facebookapi.entity.Post;
import com.example.facebookapi.entity.User;
import com.example.facebookapi.exceptions.PostException;
import com.example.facebookapi.exceptions.PostNotExist;
import com.example.facebookapi.exceptions.UserNotExist;
import com.example.facebookapi.exceptions.UsernameNotExist;
import com.example.facebookapi.mappers.PostMapper;
import com.example.facebookapi.repository.CommentRepository;
import com.example.facebookapi.repository.PostRepository;
import com.example.facebookapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;


        public PostDto savePost (PostDto postDto) {
            Optional<User> userFromDB = userRepository.findByUserName(postDto.getUserName());
            if (userFromDB.isEmpty()) {
                throw new UsernameNotExist(postDto.getUserName());
            }

            if ((postDto.getDescription() == null || postDto.getDescription().isBlank()) &&
                (postDto.getPostImgURL() == null || postDto.getPostImgURL().isBlank())) {
            throw new PostException();
        }


        LocalDateTime time = LocalDateTime.now();

            Post newPost = postMapper.dtoToPost(postDto);
            newPost.setPostID(UUID.randomUUID());
            newPost.setUserID(userFromDB.get().getUserID());
            newPost.setImageURL(userFromDB.get().getUserImage());
            newPost.setLikes(0);
            newPost.setDateTime(time);
            postRepository.save(newPost);

            return postMapper.toPostDto(newPost);

    }


    public List<PostDto> getPosts(){
        List<Post> posts = postRepository.findAll();
        return postMapper.toPostDtos(posts);
    }

    public List<PostDto> deletePost(UUID postID){
        Optional<Post> post = postRepository.findById(postID);
        if (post.isEmpty()){
            throw new PostNotExist(postID);
        }

        List<Comment> commentsToDelete = commentService.getCommentsByPostID(postID);
        commentRepository.deleteAll(commentsToDelete);
        postRepository.deleteById(postID);

        return getPosts();
    }

    public List<PostDto> getUserPosts (UUID userID){
        Optional<User> userFromDB = userRepository.findById(userID);
        if (userFromDB.isEmpty()) {
            throw new UserNotExist(userID);
        }

        List<Post> userPosts = postRepository.findAllByUserID(userID);
        return postMapper.toPostDtos(userPosts);
    }

    public List<PostDto> deleteUserPosts (UUID userID){
        Optional<User> userFromDB = userRepository.findById(userID);
        if (userFromDB.isEmpty()) {
            throw new UserNotExist(userID);
        }

        List<Post> userPosts = postRepository.findAllByUserID(userID);

        userPosts.forEach(post -> {
            List<Comment> commentsToDelete = commentService.getCommentsByPostID(post.getPostID());

            commentRepository.deleteAll(commentsToDelete);
            postRepository.delete(post);
        });

        return getPosts();
    }
}
