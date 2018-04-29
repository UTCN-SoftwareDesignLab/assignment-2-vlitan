package main.service;

import main.model.Book;
import main.repository.BookRepository;
import main.util.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellingServiceImpl implements SellingService {
    @Autowired
    private BookService bookService;

    @Override
    public Notification<Boolean> sell(Book book, int quantity) {
        return sellById(book.getId(), quantity);
    }

    @Override
    public Notification<Boolean> sellById(int id, int quantity) {
        Optional<Book> foundBook = bookService.findById(id);
        Notification<Boolean> sellNotification = new Notification<>();
        sellNotification.setResult(Boolean.FALSE);
        if (foundBook.isPresent()){
            Book soldBook = foundBook.get();
            if (soldBook.getQuantity() >= quantity) {
                soldBook.setPrice(soldBook.getPrice() - quantity);
                bookService.save(soldBook);
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
}
