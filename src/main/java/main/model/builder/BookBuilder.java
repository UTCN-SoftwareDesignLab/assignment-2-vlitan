package main.model.builder;

import main.model.Book;

public final class BookBuilder {
    public Integer id = new Integer(0);
    private String title = "default";
    private String author = "default";
    private String genre = "default";
    private String description = "default";
    private int quantity = 0;
    private int price = 0;

    private BookBuilder() {
    }

    public static BookBuilder aBook() {
        return new BookBuilder();
    }

    public BookBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public BookBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder withAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookBuilder withGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public BookBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public BookBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public BookBuilder withPrice(int price) {
        this.price = price;
        return this;
    }

    public Book build() {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setDescription(description);
        book.setQuantity(quantity);
        book.setPrice(price);
        return book;
    }
}
