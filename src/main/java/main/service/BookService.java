package main.service;

import main.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    //decreases the quantity of a certain book by 1
    public Boolean sell(Book book){
        Optional<Book> foundBook = bookRepository.findById(book.getId());
        if (foundBook.isPresent()){
            Book soldBook = foundBook.get();
            soldBook.setQuantity(soldBook.getQuantity() - 1);
            if (soldBook.getQuantity() >= 0) {
                this.save(soldBook);
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public Optional<Book> findById(Integer id){
        return bookRepository.findById(id);
    }

    public List<Book> findByTitle(String title){
        return bookRepository.findByTitle(title);
    }

    List<Book> findByQuantity(int quantity){
        return bookRepository.findByQuantity(quantity);
    }
    List<Book> findByGenre(String genre){
        return bookRepository.findByGenre(genre);
    }
    List<Book> findByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }

    //NOTE this also does update internally
    public void save(Book book){//TODO add validator
        bookRepository.save(book);
    }

    public void delete(Book book){
        bookRepository.delete(book);
    }

    public void deleteById(Integer id){
        bookRepository.deleteById(id);
    }
}
