package main.service;

import main.model.validator.BookValidator;
import main.util.Notification;
import main.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookValidator bookValidator;
    @Override
    public List<Book> findAll(){
        return bookRepository.findAll();
    }
    @Override
    //decreases the quantity of a certain book by 1
    public Notification<Boolean> sell(Book book){
        return sellById(book.getId());
    }

    //decreases the quantity of a certain book by 1
    public Notification<Boolean> sellById(int id){
        Optional<Book> foundBook = bookRepository.findById(id);
        Notification<Boolean> sellNotification = new Notification<>();
        sellNotification.setResult(Boolean.FALSE);
        if (foundBook.isPresent()){
            Book soldBook = foundBook.get();
            if (soldBook.getQuantity() > 0) {
                this.save(soldBook);
                sellNotification.setResult(Boolean.TRUE);
            }
            else{//not enough quantity
                sellNotification.addError("This book is not in stock");
            }
        }
        else{//book not present
            sellNotification.addError("Book not found");
        }
        return sellNotification;
    }
    @Override
    public Optional<Book> findById(Integer id){
        return bookRepository.findById(id);
    }
    @Override
    public List<Book> findByTitle(String title){
        return bookRepository.findByTitle(title);
    }
    @Override
    public List<Book> findByQuantity(int quantity){
        return bookRepository.findByQuantity(quantity);
    }
    @Override
    public List<Book> findByGenre(String genre){
        return bookRepository.findByGenre(genre);
    }
    @Override
    public List<Book> findByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }
    @Override
    //NOTE this also does update internally
    public Notification<Boolean> save(Book book){
        Notification<Boolean> saveNotification = new Notification<>();
        try {
            bookRepository.save(book);
            saveNotification.setResult(Boolean.TRUE);
        }
        catch (Exception e){
            saveNotification.addError("Something went bad while saving");
            saveNotification.setResult(Boolean.FALSE);
        }
        return saveNotification;
    }

    @Override
    public Notification<Boolean> deleteById(Integer id){
        Notification<Boolean> deleteNotification = new Notification<>();
        if (id == null){
            deleteNotification.setResult(Boolean.FALSE);
            deleteNotification.addError("Null id");
            return deleteNotification;
        }
        if (id.intValue()>0){
            try{
                bookRepository.deleteById(id);
                deleteNotification.setResult(Boolean.TRUE);
            }
            catch (Exception e){
                deleteNotification.setResult(Boolean.FALSE);
                deleteNotification.addError("Something went bad while deleting");
            }
        }
        else{
            deleteNotification.setResult(Boolean.TRUE);
            deleteNotification.addError("Id cannot be negative");
        }
        return deleteNotification;
    }
}
