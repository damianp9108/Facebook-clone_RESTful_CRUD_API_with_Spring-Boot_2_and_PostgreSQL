package facebookapi.business;

import facebookapi.business.exceptions.CommentNotExistException;
import facebookapi.business.exceptions.PostNotExistException;
import facebookapi.business.exceptions.UserNotExistException;
import facebookapi.domain.entity.Comment;
import facebookapi.domain.entity.Post;
import facebookapi.domain.entity.User;
import facebookapi.domain.repository.CommentRepository;
import facebookapi.domain.repository.PostRepository;
import facebookapi.domain.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class IdChecker {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public Comment isCommentAvailable(int commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotExistException(commentId));

        return comment;
    }

    public User isUserAvailable(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistException(userId));

        return user;
    }

    public Post isPostAvailable(int postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotExistException(postId));

        return post;
    }
}
