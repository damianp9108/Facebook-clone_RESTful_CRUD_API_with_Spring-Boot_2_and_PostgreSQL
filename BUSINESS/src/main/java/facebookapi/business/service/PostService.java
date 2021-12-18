package facebookapi.business.service;

import facebookapi.business.dto.NewPostDto;
import facebookapi.business.dto.PostDto;
import facebookapi.business.exceptions.PostException;
import facebookapi.business.exceptions.PostNotExistException;
import facebookapi.business.exceptions.UserNotExistException;
import facebookapi.business.mappers.CommentMapper;
import facebookapi.business.mappers.PostMapper;
import facebookapi.domain.entity.Post;
import facebookapi.domain.entity.User;
import facebookapi.domain.repository.CommentRepository;
import facebookapi.domain.repository.PostRepository;
import facebookapi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;


    public PostDto savePost(NewPostDto newPostDto) {
        User userFromDB = userRepository.findById(newPostDto.getUserId())
                .orElseThrow(() -> new UserNotExistException(newPostDto.getUserId()));


        if ((newPostDto.getDescription() == null || newPostDto.getDescription().isBlank()) &&
                (newPostDto.getPostImgURL() == null || newPostDto.getPostImgURL().isBlank())) {
            throw new PostException();
        }

        Post newPost = postMapper.dtoToPost(newPostDto);

        var savedPost = postRepository.save(newPost);

        return postMapper.toPostDto(savedPost);

    }


    public List<PostDto> getPosts() {
        List<Post> posts = postRepository.findAll();
        return postMapper.toPostsDto(posts);
    }

    public String deletePost(int postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotExistException(postId));

/*
        List<CommentDto> commentsToDelete = commentService.getCommentsByPostID(postID);
        List<Comment> comments = commentMapper.dtosToComments(commentsToDelete);
        commentRepository.deleteAll(comments);
*/

        postRepository.deleteById(postId);

        return "Post zostal pomyslnie usuniety";
    }

    public List<PostDto> getUserPosts(int userId) {
        User userFromDB = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistException(userId));

        List<Post> userPosts = postRepository.findByUser(userFromDB);
        return postMapper.toPostsDto(userPosts);
    }

    public String deleteUserPosts(int userId) {
        User userFromDB = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistException(userId));

        List<Post> userPosts = postRepository.findByUser(userFromDB);

        userPosts.forEach(post -> {
            // List<CommentDto> commentsToDelete = commentService.getCommentsByPostID(post.getPostID());
            // List<Comment> comments = commentMapper.dtosToComments(commentsToDelete);

            // commentRepository.deleteAll(comments);
            postRepository.delete(post);
        });

        return "Usunieto pomyslnie wszystkie posty uzytkownika o numerze Id: " + userId;
    }
}
