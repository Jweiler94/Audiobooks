package JW.projects.Audiobooks.controllers;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import JW.projects.Audiobooks.models.User;
import JW.projects.Audiobooks.repositories.BooksRepository;
import JW.projects.Audiobooks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(
        origins = {"http://localhost:5173/"}
)
@RequestMapping({"/api/users"})
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BooksRepository booksRepository;
    private static final String userSessionKey = "user";

    public UserController() {
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = this.userRepository.findById(id);
        return (ResponseEntity<User>)user.map(ResponseEntity::ok).orElseGet(() -> {
            return ResponseEntity.notFound().build();
        });
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Object> updateUser(@PathVariable int id, @RequestBody @Valid User updatedUser, BindingResult result) {
        if (!result.hasErrors()) {
            Optional<User> existingUser = this.userRepository.findById(id);
            if (existingUser.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                User existingUserToUpdate = (User)existingUser.get();
                existingUserToUpdate.setUsername(updatedUser.getUserName());
                existingUserToUpdate.setEmail(updatedUser.getEmail());
                if (!updatedUser.isPasswordEmpty()) {
                    existingUserToUpdate.setPassword(User.getEncoder().encode(updatedUser.getPassword()));
                }

                this.userRepository.save(existingUserToUpdate);
                return ResponseEntity.ok(existingUserToUpdate);
            }
        } else {
            Map<String, String> errors = new HashMap<>();

            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.unprocessableEntity().body(errors);
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        this.userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
