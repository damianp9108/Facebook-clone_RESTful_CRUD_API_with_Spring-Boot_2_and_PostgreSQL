package facebookapi.business.service;

import facebookapi.business.IdChecker;
import facebookapi.business.dto.CommentDto;
import facebookapi.business.dto.NewCommentDto;
import facebookapi.business.mappers.CommentMapper;
import facebookapi.domain.entity.Comment;
import facebookapi.domain.entity.Post;
import facebookapi.domain.entity.User;
import facebookapi.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final IdChecker idChecker;

    public CommentDto saveComment(NewCommentDto newComment) {
        idChecker.isUserAvailable(newComment.getUserId());
        idChecker.isPostAvailable(newComment.getPostId());

        Comment comment = commentMapper.dtoToComment(newComment);
        var savedComment = commentRepository.save(comment);

        return commentMapper.toCommentDto(savedComment);
    }

    public List<CommentDto> getCommentsByPostId(int postId) {
        Post postAvailable = idChecker.isPostAvailable(postId);
        List<Comment> comments = commentRepository.findAllByPost(postAvailable);

        return commentMapper.toCommentsDto(comments);
    }

    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDto> commentsDto = commentMapper.toCommentsDto(comments);

        return commentsDto;
    }

    public String deleteComment(int commentId) {
        idChecker.isCommentAvailable(commentId);
        commentRepository.deleteById(commentId);

        return "Komentarz o numerze Id: " + commentId + " zostal pomyslnie usuniety.";
    }

    public List<CommentDto> getCommentsByUserId(int userId) {
        User userAvailable = idChecker.isUserAvailable(userId);

        List<Comment> userComments = commentRepository.findAllByUser(userAvailable);
        List<CommentDto> commentsDto = commentMapper.toCommentsDto(userComments);

        return commentsDto;
    }

    public String deleteCommentsByUserId(int userId) {
        User userAvailable = idChecker.isUserAvailable(userId);
        List<Comment> userComments = commentRepository.findAllByUser(userAvailable);
        commentRepository.deleteAll(userComments);

        return "Usunieto wszystkie komentarze dodane przez uzytkownika o numerze Id: " + userId;
    }


}
