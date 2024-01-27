package JW.projects.Audiobooks.controllers;

import JW.projects.Audiobooks.repositories.BooksRepository;
import JW.projects.Audiobooks.repositories.UserRepository;
import JW.projects.Audiobooks.models.User;
import JW.projects.Audiobooks.dtos.RegisterFormDTO;
import JW.projects.Audiobooks.dtos.LoginFormDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(
        origins = {"http://localhost:5173/"}
)
public class ApiController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static final String userSessionKey = "user";
    private final BooksRepository booksRepository;

    @Autowired
    public ApiController(BooksRepository booksRepository, UserRepository userRepository) {
        this.booksRepository = booksRepository;
        this.userRepository = userRepository;
    }

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer)session.getAttribute("user");
        if (userId == null) {
            return null;
        } else {
            Optional<User> user = this.userRepository.findById(userId);
            return (User) user.orElse(null);
        }
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute("user", user.getId());
    }

    public ResponseEntity<User> getCurrentUser(HttpSession session) {
        User user = this.getUserFromSession(session);
        return user == null ? new ResponseEntity<>(HttpStatus.UNAUTHORIZED) : new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping({"/currentUserId"})
    public ResponseEntity<Integer> getCurrentUserId(HttpSession session) {
        User user = this.getUserFromSession(session);
        return user == null ? new ResponseEntity<>(HttpStatus.UNAUTHORIZED) : new ResponseEntity<>(user.getId(), HttpStatus.OK);
    }

    @PostMapping({"/register"})
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterFormDTO registerFormDTO, Errors errors, HttpServletRequest request) {
        if (errors.hasErrors()) {
            System.out.println(errors.getAllErrors());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            User existingUser = this.userRepository.findByUsernameIgnoreCase(registerFormDTO.getUsername());
            if (existingUser != null) {
                return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
            } else {
                String password = registerFormDTO.getPassword();
                String verifyPassword = registerFormDTO.getVerifyPassword();
                if (!password.equals(verifyPassword)) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                } else {
                    User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getEmail(), this.passwordEncoder.encode(password));
                    newUser.setUsername(registerFormDTO.getUsername());
                    this.userRepository.save(newUser);
                    setUserInSession(request.getSession(), newUser);
                    return new ResponseEntity<>(newUser, HttpStatus.CREATED);
                }
            }
        }
    }

    @GetMapping({"/login"})
    public ResponseEntity<String> displayLoginForm() {
        return new ResponseEntity<>("Please log in", HttpStatus.OK);
    }

    @PostMapping({"/login"})
    public ResponseEntity<User> processLoginForm(@RequestBody @Valid LoginFormDTO loginFormDTO, Errors errors, HttpServletRequest request) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            User theUser = this.userRepository.findByUsernameIgnoreCase(loginFormDTO.getUsername());
            if (theUser != null && theUser.isMatchingPassword(loginFormDTO.getPassword())) {
                setUserInSession(request.getSession(), theUser);
                System.out.println("User authenticated: " + theUser.getUserName());
                return new ResponseEntity<>(theUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @GetMapping({"/logout"})
    public ResponseEntity<String> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
    }
}
