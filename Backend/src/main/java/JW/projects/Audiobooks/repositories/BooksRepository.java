package JW.projects.Audiobooks.repositories;

import java.util.List;
import JW.projects.Audiobooks.models.Book;
import JW.projects.Audiobooks.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findByOwner(User owner);
}
