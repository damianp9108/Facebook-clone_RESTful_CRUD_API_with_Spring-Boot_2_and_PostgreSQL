package facebookapi.business.service;

import facebookapi.business.dto.CommentDto;
import facebookapi.business.dto.CommentDtoRequestBody;
import facebookapi.domain.entity.Comment;
import facebookapi.domain.entity.Post;
import facebookapi.domain.entity.User;
import facebookapi.business.mappers.UserMapper;
import facebookapi.domain.repository.CommentRepository;
import facebookapi.domain.repository.PostRepository;
import facebookapi.domain.repository.UserRepository;
import facebookapi.business.exceptions.CommentNotExist;
import facebookapi.business.exceptions.PostNotExist;
import facebookapi.business.exceptions.UserNotExist;
import facebookapi.business.mappers.CommentMapper;
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

    public Post checkPost(int postID){
        Optional<Post> post = postRepository.findById(postID);
        if (post.isEmpty()){
            throw new PostNotExist(postID);
        }
        return post.get();
    }

    public void checkComment(int commentID){
        Optional<Comment> comment = commentRepository.findById(commentID);
        if (comment.isEmpty()){
            throw new CommentNotExist(commentID);
        }
    }

    public CommentDto saveComment(CommentDtoRequestBody newComment){
        Optional<User> user = userRepository.findById(newComment.getUserID());
        if (user.isEmpty()){
            throw new UserNotExist(newComment.getUserID());
        }
        checkPost(newComment.getPostID());

        Comment comment = new Comment();

        LocalDateTime dateTime = LocalDateTime.now();
        comment.setUser(user.get());
        Post post = postRepository.getById(newComment.getPostID());
        comment.setPost(post);
        comment.setTime(dateTime);
        comment.setComment(newComment.getComment());

        commentRepository.save(comment);

        return commentMapper.toCommentDto(comment);
    }

    public List<CommentDto> getCommentsByPostID(int postID){
        Post post = checkPost(postID);
        List<Comment> comments = commentRepository.findAllByPost(post);
        return commentMapper.toCommentDtos(comments);
    }

    public List<CommentDto> getAllComments(){
        List<Comment> comments = commentRepository.findAll();
        List<CommentDto> commentDtos = commentMapper.toCommentDtos(comments);
        return commentDtos;
    }

    public List<CommentDto> deleteComment(int commentID){
        checkComment(commentID);
        commentRepository.deleteById(commentID);
        return getAllComments();
    }

    public List<CommentDto> getCommentsByUserID (int userID){
        Optional<User> user = userRepository.findById(userID);
        if (user.isEmpty()) {
            throw new UserNotExist(userID);
        }

        List<Comment> userComments = commentRepository.findAllByUser(user.get());
        List<CommentDto> commentDtos = commentMapper.toCommentDtos(userComments);
        return commentDtos;
    }

    public List<CommentDto> deleteCommentsByUserID (int userID){
        Optional<User> user = userRepository.findById(userID);
        if (user.isEmpty()) {
            throw new UserNotExist(userID);
        }

        List<Comment> userComments = commentRepository.findAllByUser(user.get());
        commentRepository.deleteAll(userComments);
        return getAllComments();
    }


}
