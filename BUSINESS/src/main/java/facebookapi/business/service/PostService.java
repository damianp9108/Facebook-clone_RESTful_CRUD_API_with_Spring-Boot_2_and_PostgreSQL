package facebookapi.business.service;

import facebookapi.business.IdChecker;
import facebookapi.business.payload.request.NewPostRequest;
import facebookapi.business.dto.PostDto;
import facebookapi.business.mappers.PostMapper;
import facebookapi.business.security.services.UserDetailsImpl;
import facebookapi.domain.entity.Post;
import facebookapi.domain.entity.User;
import facebookapi.domain.repository.PostRepository;
import facebookapi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final IdChecker idChecker;
    private final UserRepository userRepository;
    private final UserService userService;


    public PostDto savePost(NewPostRequest newPostRequest) {

        Post newPost = postMapper.toPost(newPostRequest);
        var savedPost = postRepository.save(newPost);

        return postMapper.toPostDto(savedPost);
    }


    public List<PostDto> getPosts() {
        List<Post> posts = postRepository.findAll();

        List<PostDto> postsDto = postMapper.toPostsDto(posts).stream()
                .sorted((e1, e2) -> e2.getDateTime().compareTo(e1.getDateTime()))
                .toList();

        return postsDto;
    }


    public String deletePost(int postId) {
        Post post = idChecker.checkPostAvailable(postId);
        int user_Id = post.getUser().getUserId();

        int userId = userService.retrieveCurrentlyAuthenticatedUserId();
        if (user_Id == userId){
            postRepository.deleteById(postId);

            return "Post zostal pomyslnie usuniety.";

        } else
            return "Error: Mozesz usunac tylko swoj post!";
    }


    public List<PostDto> getUserPosts(int userId) {
        User userAvailable = idChecker.checkUserAvailable(userId);
        List<Post> userPosts = postRepository.findByUser(userAvailable);

        return postMapper.toPostsDto(userPosts);
    }

    public String deleteUserPosts() {
        int user_Id = userService.retrieveCurrentlyAuthenticatedUserId();
        Optional<User> user = userRepository.findById(user_Id);

        List<Post> userPosts = postRepository.findByUser(user.get());

            userPosts.forEach(post -> {
                postRepository.delete(post);
            });

            return "Usunieto pomyslnie wszystkie Twoje posty!";
    }

}
