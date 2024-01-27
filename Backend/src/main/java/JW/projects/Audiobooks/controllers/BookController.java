package JW.projects.Audiobooks.controllers;

import JW.projects.Audiobooks.dtos.BooksDTO;
import JW.projects.Audiobooks.models.Book;
import JW.projects.Audiobooks.models.User;
import JW.projects.Audiobooks.repositories.*;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(
        origins = {"http://localhost:5173/"}
)
@RequestMapping({"/api/books"})
public class BookController {

    private static final String userSessionKey = "user";
    private final UserRepository userRepository;
    private final BooksRepository booksRepository;

    public BookController(UserRepository userRepository, BooksRepository booksRepository) {
        this.userRepository = userRepository;
        this.booksRepository = booksRepository;
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

    @GetMapping({"/{userId}"})
    public ResponseEntity<List<Book>> getBooksByUserId(@PathVariable int userId, HttpSession session) {
        Optional<User> user = this.userRepository.findById(userId);
        User owner = this.getUserFromSession(session);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else if (owner == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            List<Book> books = this.booksRepository.findByOwner(owner);
            return books.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(books);
        }
    }

    @PostMapping({"/create"})
    public ResponseEntity<?> createBook(@RequestBody @Valid BooksDTO booksDTO, Errors errors, HttpSession session) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            User owner = this.getUserFromSession(session);
            if (owner == null) {
                return new ResponseEntity<>("You must be logged in to save a book!", HttpStatus.UNAUTHORIZED);
            } else {
                Book book = new Book(owner, booksDTO.getTitle(), booksDTO.getDescription(), booksDTO.getUrl_text_source(), booksDTO.getLanguage(), booksDTO.getCopyright_year(), booksDTO.getNum_sections(), booksDTO.getUrl_zip_file(), booksDTO.getUrl_rss(), booksDTO.getUrl_project(), booksDTO.getUrl_librivox(), booksDTO.getUrl_iarchive(), booksDTO.getUrl_other(), booksDTO.getTotaltime(), booksDTO.getTotaltimesecs(), booksDTO.getAuthors(), booksDTO.getSections(), booksDTO.getGenres(), booksDTO.getTranslators());

                try {
                    this.booksRepository.save(book);
                    return new ResponseEntity<>(book, HttpStatus.OK);
                } catch (Exception var7) {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
    }

    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity<?> deleteBook(@PathVariable Integer id) {
        Optional<Book> optionalBook = this.booksRepository.findById(id);
        if (optionalBook.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            this.booksRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
