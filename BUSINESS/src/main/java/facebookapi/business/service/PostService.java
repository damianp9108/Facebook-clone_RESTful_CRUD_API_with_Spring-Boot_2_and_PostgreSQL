package facebookapi.business.service;

import facebookapi.domain.entity.Post;
import facebookapi.domain.entity.User;
import facebookapi.business.mappers.PostMapper;
import facebookapi.domain.repository.CommentRepository;
import facebookapi.domain.repository.PostRepository;
import facebookapi.domain.repository.UserRepository;
import facebookapi.business.exceptions.PostException;
import facebookapi.business.exceptions.PostNotExistException;
import facebookapi.business.exceptions.UserNotExistException;
import facebookapi.business.exceptions.UsernameNotExistException;
import facebookapi.business.dto.PostDto;
import facebookapi.business.mappers.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;


    public PostDto savePost(PostDto postDto) {
        Optional<User> userFromDB = userRepository.findByUserName(postDto.getUserDto().getUserName());
        if (userFromDB.isEmpty()) {
            throw new UsernameNotExistException(postDto.getUserDto().getUserName());
        }

        if ((postDto.getDescription() == null || postDto.getDescription().isBlank()) &&
                (postDto.getPostImgURL() == null || postDto.getPostImgURL().isBlank())) {
            throw new PostException();
        }


        LocalDateTime time = LocalDateTime.now();

        Post newPost = postMapper.dtoToPost(postDto);
        newPost.setUser(userFromDB.get());
        newPost.setLikes(0);
        newPost.setDateTime(time);

        postRepository.save(newPost);

        return postMapper.toPostDto(newPost);

    }


    public List<PostDto> getPosts() {
        List<Post> posts = postRepository.findAll();
        return postMapper.toPostDtos(posts);
    }

    public List<PostDto> deletePost(int postID) {
        Optional<Post> post = postRepository.findById(postID);
        if (post.isEmpty()) {
            throw new PostNotExistException(postID);
        }
/*
        List<CommentDto> commentsToDelete = commentService.getCommentsByPostID(postID);
        List<Comment> comments = commentMapper.dtosToComments(commentsToDelete);
        commentRepository.deleteAll(comments);
*/

        postRepository.deleteById(postID);

        return getPosts();
    }

    public List<PostDto> getUserPosts(int userID) {
        Optional<User> userFromDB = userRepository.findById(userID);
        if (userFromDB.isEmpty()) {
            throw new UserNotExistException(userID);
        }

        List<Post> userPosts = postRepository.findByUser(userFromDB.get());
        return postMapper.toPostDtos(userPosts);
    }

    public List<PostDto> deleteUserPosts(int userID) {
        Optional<User> userFromDB = userRepository.findById(userID);
        if (userFromDB.isEmpty()) {
            throw new UserNotExistException(userID);
        }

        List<Post> userPosts = postRepository.findByUser(userFromDB.get());

        userPosts.forEach(post -> {
            // List<CommentDto> commentsToDelete = commentService.getCommentsByPostID(post.getPostID());
            // List<Comment> comments = commentMapper.dtosToComments(commentsToDelete);

            // commentRepository.deleteAll(comments);
            postRepository.delete(post);
        });

        return getPosts();
    }
}
