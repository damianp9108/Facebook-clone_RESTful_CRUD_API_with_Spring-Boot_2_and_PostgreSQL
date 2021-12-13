package facebookapi.business.service;

import facebookapi.business.dto.CommentDto;
import facebookapi.business.dto.CommentDtoRequestBody;
import facebookapi.business.exceptions.CommentNotExistException;
import facebookapi.business.exceptions.PostNotExistException;
import facebookapi.business.exceptions.UserNotExistException;
import facebookapi.business.mappers.CommentMapper;
import facebookapi.business.mappers.UserMapper;
import facebookapi.domain.entity.Comment;
import facebookapi.domain.entity.Post;
import facebookapi.domain.entity.User;
import facebookapi.domain.repository.CommentRepository;
import facebookapi.domain.repository.PostRepository;
import facebookapi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;
    private final UserMapper userMapper;

    public Post checkPost(int postId){
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()){
            throw new PostNotExistException(postId);
        }
        return post.get();
    }

    public void checkComment(int commentId){
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isEmpty()){
            throw new CommentNotExistException(commentId);
        }
    }

    public CommentDto saveComment(CommentDtoRequestBody newComment){
        Optional<User> user = userRepository.findById(newComment.getUserId());
        if (user.isEmpty()){
            throw new UserNotExistException(newComment.getUserId());
        }
        checkPost(newComment.getPostId());

        Comment comment = new Comment();

        LocalDateTime dateTime = LocalDateTime.now();
        comment.setUser(user.get());
        Post post = postRepository.getById(newComment.getPostId());
        comment.setPost(post);
        comment.setTime(dateTime);
        comment.setComment(newComment.getComment());

        commentRepository.save(comment);

        return commentMapper.toCommentDto(comment);
    }

    public List<CommentDto> getCommentsByPostId(int postId){
        Post post = checkPost(postId);
        List<Comment> comments = commentRepository.findAllByPost(post);
        return commentMapper.toCommentDtos(comments);
    }

    public List<CommentDto> getAllComments(){
        List<Comment> comments = commentRepository.findAll();
        List<CommentDto> commentDtos = commentMapper.toCommentDtos(comments);
        return commentDtos;
    }

    public List<CommentDto> deleteComment(int commentId){
        checkComment(commentId);
        commentRepository.deleteById(commentId);
        return getAllComments();
    }

    public List<CommentDto> getCommentsByUserId (int userId){
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotExistException(userId);
        }

        List<Comment> userComments = commentRepository.findAllByUser(user.get());
        List<CommentDto> commentDtos = commentMapper.toCommentDtos(userComments);
        return commentDtos;
    }

    public List<CommentDto> deleteCommentsByUserId (int userId){
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotExistException(userId);
        }

        List<Comment> userComments = commentRepository.findAllByUser(user.get());
        commentRepository.deleteAll(userComments);
        return getAllComments();
    }


}
