package facebookapi.business.service;

import facebookapi.business.dto.CommentDto;
import facebookapi.business.dto.NewCommentDto;
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



    public void checkComment(int commentId){
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isEmpty()){
            throw new CommentNotExistException(commentId);
        }
    }

    public CommentDto saveComment(NewCommentDto newComment){
        User userFromDB = userRepository.findById(newComment.getUserId())
                .orElseThrow(() -> new UserNotExistException(newComment.getUserId()));

        Post postFromDB = postRepository.findById(newComment.getPostId())
                .orElseThrow(() -> new PostNotExistException(newComment.getPostId()));

        Comment comment = commentMapper.dtoToComment(newComment);
        var savedComment = commentRepository.save(comment);

        return commentMapper.toCommentDto(savedComment);
    }

    public List<CommentDto> getCommentsByPostId(int postId){
        Post postFromDB = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotExistException(postId));
        List<Comment> comments = commentRepository.findAllByPost(postFromDB);
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
