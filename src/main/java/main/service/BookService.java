package main.service;

import main.util.Notification;
import main.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public List<Book> findAll();
    public Notification<Boolean> sell(Book book);
    public Notification<Boolean> sellById(int id);
    public Optional<Book> findById(Integer id);
    public List<Book> findByTitle(String title);
    public List<Book> findByQuantity(int quantity);
    public List<Book> findByGenre(String genre);
    public List<Book> findByAuthor(String author);
    //NOTE this also does update internally
    public void save(Book book);
    public void delete(Book book);
    public void deleteById(Integer id);
}
