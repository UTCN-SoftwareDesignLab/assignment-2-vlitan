package main.repository;


import main.model.Book;
import main.model.User;
import main.model.Role;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockData {
    public final static List<Book> books = new ArrayList<>(Arrays.asList(
            new Book("book1", "author1", "genre1", "description1", 0, 0),
            new Book("book2", "author1", "genre1", "description1", 2, 1),
            new Book("book2", "author1", "genre1", "description2", 34, 2),
            new Book("book3", "author2", "genre1", "", 1, 23),
            new Book("book4", "author2", "genre2", "description1", 1, 1),
            new Book("book5", "author3", "genre2", "description1", 3, 0),
            new Book("book6", "author4", "genre3", "description3", 4, 3)
    ));
    public final static List<User> users = new ArrayList<>(Arrays.asList(
            new User("admin", 16, "asdf",Role.ADMIN, false),
            new User("user1", 16, "asdf",Role.USER,false),
            new User("user2", 16, "asdf",Role.USER,false),
            new User("user3", 16, "asdf",Role.USER,false)
    ));
    public final static Book newBook = new Book("new book", "author4", "genre3", "description1", 4, 3);
    public final static User newUser =  new User("newUser", 30, "adminAD12!@",Role.USER, false);
}
