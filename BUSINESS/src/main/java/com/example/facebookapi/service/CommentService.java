package com.example.facebookapi.service;

import com.example.facebookapi.dto.CommentDto;
import com.example.facebookapi.entity.Comment;
import com.example.facebookapi.entity.Post;
import com.example.facebookapi.entity.User;
import com.example.facebookapi.exceptions.CommentNotExist;
import com.example.facebookapi.exceptions.PostNotExist;
import com.example.facebookapi.exceptions.UsernameNotExist;
import com.example.facebookapi.mappers.CommentMapper;
import com.example.facebookapi.repository.CommentRepository;
import com.example.facebookapi.repository.PostRepository;
import com.example.facebookapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    public void checkPost(UUID postID){
        Optional<Post> post = postRepository.findById(postID);
        if (post.isEmpty()){
            throw new PostNotExist(postID);
        }
    }

    public void checkComment(UUID commentID){
        Optional<Comment> comment = commentRepository.findById(commentID);
        if (comment.isEmpty()){
            throw new CommentNotExist(commentID);
        }
    }

    public CommentDto saveComment(CommentDto commentDto){
        Optional<User> user = userRepository.findByUserName(commentDto.getUserName());
        if (user.isEmpty()){
            throw new UsernameNotExist(commentDto.getUserName());
        }
        checkPost(commentDto.getPostID());

        Comment comment = commentMapper.dtoToComment(commentDto);

        LocalDateTime dateTime = LocalDateTime.now();
        comment.setCommentID(UUID.randomUUID());
        comment.setUserID(user.get().getUserID());
        comment.setUserImage(user.get().getUserImage());
        comment.setTime(dateTime);

        commentRepository.save(comment);

        return commentMapper.toCommentDto(comment);
    }

    public List<CommentDto> getCommentsByPostID(UUID postID){
        checkPost(postID);
        List<Comment> comments = commentRepository.findAllByPostID(postID);
        return commentMapper.toCommentDtos(comments);
    }

    public List<CommentDto> getAllComments(){
        List<Comment> comments = commentRepository.findAll();
        List<CommentDto> commentDtos = commentMapper.toCommentDtos(comments);
        return commentDtos;
    }

    public List<CommentDto> deleteComment(UUID commentID){
        checkComment(commentID);
        commentRepository.deleteById(commentID);
        return getAllComments();
    }

    public List<CommentDto> getCommentsByUserID (UUID userID){
        userService.getUser(userID);
        List<Comment> userComments = commentRepository.findAllByUserID(userID);
        List<CommentDto> commentDtos = commentMapper.toCommentDtos(userComments);
        return commentDtos;
    }

    public List<CommentDto> deleteCommentsByUserID (UUID userID){
        userService.getUser(userID);
        List<Comment> toDelete = commentRepository.findAllByUserID(userID);
        commentRepository.deleteAll(toDelete);
        return getAllComments();
    }
}
