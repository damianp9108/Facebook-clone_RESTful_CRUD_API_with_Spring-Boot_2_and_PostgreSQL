package facebookapi.business.service;

import facebookapi.business.IdChecker;
import facebookapi.business.dto.UserDto;
import facebookapi.business.exceptions.EmailAlreadyExistException;
import facebookapi.business.exceptions.UsernameAlreadyExistException;
import facebookapi.business.mappers.UserMapper;
import facebookapi.business.payload.request.LoginRequest;
import facebookapi.business.payload.request.SignupRequest;
import facebookapi.business.payload.response.JwtResponse;
import facebookapi.business.security.jwt.JwtUtils;
import facebookapi.business.security.services.UserDetailsImpl;
import facebookapi.domain.entity.User;
import facebookapi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final IdChecker idChecker;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    public String saveUser(SignupRequest signupRequest) {
        if (userRepository.existsByUserName(signupRequest.getUserName())) {
            throw new UsernameAlreadyExistException(signupRequest.getUserName());
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new EmailAlreadyExistException(signupRequest.getEmail());
        }

        User newUser = userMapper.dtoToUser(signupRequest);
        var savedUser = userRepository.save(newUser);

        return "Uzytkownik zarejestrowany pomyslnie!";
    }


    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUserId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }


    public List<UserDto> getAllUsersWithPosts() {
        List<User> users = userRepository.findAll();

        return userMapper.toUsersDto(users);
    }

    public List<String> getUserNamesList() {
        List<String> userNamesList = userRepository.findAll().stream()
                .map(User::getUserName)
                .collect(Collectors.toList());

        return userNamesList;
    }

    public UserDto getUser(int userId) {
        User user = idChecker.checkUserAvailable(userId);

        return userMapper.toUserDto(user);

    }

    public int retrieveCurrentlyAuthenticatedUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return userDetails.getUserId();
    }

}
