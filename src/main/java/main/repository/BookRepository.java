package main.repository;

import main.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitle(String title);
    List<Book> findByQuantity(int quantity);
    List<Book> findByGenre(String genre);
    List<Book> findByAuthor(String author);
}
