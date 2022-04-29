package facebookapi.business.service;

import facebookapi.business.IdChecker;
import facebookapi.business.dto.CommentDto;
import facebookapi.business.mappers.CommentMapper;
import facebookapi.business.payload.request.NewCommentRequest;
import facebookapi.domain.entity.Comment;
import facebookapi.domain.entity.Post;
import facebookapi.domain.entity.User;
import facebookapi.domain.repository.CommentRepository;
import facebookapi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final IdChecker idChecker;
    private final UserService userService;
    private final UserRepository userRepository;

    public CommentDto saveComment(NewCommentRequest newComment) {
        idChecker.checkPostAvailable(newComment.getPostId());

        Comment comment = commentMapper.toComment(newComment);
        var savedComment = commentRepository.save(comment);

        return commentMapper.toCommentDto(savedComment);
    }

    public List<CommentDto> getCommentsByPostId(int postId) {
        Post postAvailable = idChecker.checkPostAvailable(postId);
        List<Comment> comments = commentRepository.findAllByPost(postAvailable);

        return commentMapper.toCommentsDto(comments);
    }

    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDto> commentsDto = commentMapper.toCommentsDto(comments);

        return commentsDto;
    }

    public String deleteComment(int commentId) {
        Comment comment = idChecker.checkCommentAvailable(commentId);
        int userId = comment.getUser().getUserId();

        int user_Id = userService.retrieveCurrentlyAuthenticatedUserId();
        if (user_Id == userId) {
            commentRepository.deleteById(commentId);

            return "Komentarz o numerze Id: " + commentId + " zostal pomyslnie usuniety.";

        } else
            return "Error: Mozesz usunac tylko swoj komentarz!";
    }

    public List<CommentDto> getCommentsByUserId(int userId){
        User userAvailable = idChecker.checkUserAvailable(userId);

        List<Comment> userComments = commentRepository.findAllByUser(userAvailable);
        List<CommentDto> commentsDto = commentMapper.toCommentsDto(userComments);

        return commentsDto;
    }

    public String deleteUserComments() {
        int userId = userService.retrieveCurrentlyAuthenticatedUserId();
        Optional<User> user = userRepository.findById(userId);

        List<Comment> userComments = commentRepository.findAllByUser(user.get());
        commentRepository.deleteAll(userComments);

            return "Usunieto wszystkie Twoje komentarze!";
    }
}
