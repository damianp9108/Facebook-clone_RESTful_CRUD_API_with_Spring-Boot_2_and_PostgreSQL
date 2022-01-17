package facebookapi.business.service;

import facebookapi.business.IdChecker;
import facebookapi.business.dto.NewPostDto;
import facebookapi.business.dto.PostDto;
import facebookapi.business.mappers.PostMapper;
import facebookapi.domain.entity.Post;
import facebookapi.domain.entity.User;
import facebookapi.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final IdChecker idChecker;

    public PostDto savePost(NewPostDto newPostDto) {
        idChecker.isUserAvailable(newPostDto.getUserId());


        Post newPost = postMapper.dtoToPost(newPostDto);
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
        idChecker.isPostAvailable(postId);
        postRepository.deleteById(postId);

        return "Post zostal pomyslnie usuniety";
    }

    public List<PostDto> getUserPosts(int userId) {
        User userAvailable = idChecker.isUserAvailable(userId);
        List<Post> userPosts = postRepository.findByUser(userAvailable);

        return postMapper.toPostsDto(userPosts);
    }

    public String deleteUserPosts(int userId) {
        User userAvailable = idChecker.isUserAvailable(userId);
        List<Post> userPosts = postRepository.findByUser(userAvailable);

        userPosts.forEach(post -> {
            postRepository.delete(post);
        });

        return "Usunieto pomyslnie wszystkie posty uzytkownika o numerze Id: " + userId;
    }
}
