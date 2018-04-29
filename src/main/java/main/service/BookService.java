package main.service;

import main.util.Notification;
import main.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public List<Book> findAll();
    public Optional<Book> findById(Integer id);
    public List<Book> findByTitle(String title);
    public List<Book> findByQuantity(int quantity);
    public List<Book> findByGenre(String genre);
    public List<Book> findByAuthor(String author);
    public Notification<Boolean>  save(Book book);//NOTE this also does update internally
    public Notification<Boolean>  deleteById(Integer id);
}
