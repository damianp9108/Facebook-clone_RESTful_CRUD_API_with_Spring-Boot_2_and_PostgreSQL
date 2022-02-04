package facebookapi.business.service;

import facebookapi.business.IdChecker;
import facebookapi.business.dto.UserDto;
import facebookapi.business.exceptions.RoleNotFoundException;
import facebookapi.business.mappers.UserMapper;
import facebookapi.domain.ERole;
import facebookapi.domain.entity.Comment;
import facebookapi.domain.entity.Post;
import facebookapi.domain.entity.Role;
import facebookapi.domain.entity.User;
import facebookapi.domain.repository.CommentRepository;
import facebookapi.domain.repository.PostRepository;
import facebookapi.domain.repository.RoleRepository;
import facebookapi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final IdChecker idChecker;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    public UserDto setRole(int userId, String roleName) {
        User user = idChecker.checkUserAvailable(userId);
        Set<Role> roles = user.getRoles();

        if (roleName.contains("admin")) {
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RoleNotFoundException());
            roles.add(adminRole);

        } else if (roleName.contains("mod")) {
            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                    .orElseThrow(() -> new RoleNotFoundException());
            roles.add(modRole);

        } else throw new RoleNotFoundException();


        var savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);
    }

    public String deleteUser(int userId) {
        idChecker.checkUserAvailable(userId);
        userRepository.deleteById(userId);

        return "Uzytkownik o numerze Id: " + userId + " zostal pomyslnie usuniety";

    }

    public String deletePost(int postId) {
        idChecker.checkPostAvailable(postId);
        postRepository.deleteById(postId);

        return "Post zostal pomyslnie usuniety.";
    }


    public String deleteUserPosts(int userId) {
        User user = idChecker.checkUserAvailable(userId);

        List<Post> userPosts = postRepository.findByUser(user);

        userPosts.forEach(post -> {
            postRepository.delete(post);
        });

        return "Usunieto pomyslnie wszystkie posty uzytkownika o numerze Id: " + userId;
    }

    public String deleteComment(int commentId) {
        idChecker.checkCommentAvailable(commentId);
        commentRepository.deleteById(commentId);

        return "Komentarz o numerze Id: " + commentId + " zostal pomyslnie usuniety.";
    }

    public String deleteUserComments(int userId) {
        User userAvailable = idChecker.checkUserAvailable(userId);
        List<Comment> userComments = commentRepository.findAllByUser(userAvailable);
        commentRepository.deleteAll(userComments);

        return "Usunieto wszystkie komentarze dodane przez uzytkownika o numerze Id: " + userId;
    }
}