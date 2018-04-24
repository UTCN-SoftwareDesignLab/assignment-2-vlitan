package main.model;

import org.springframework.lang.NonNull;

import javax.annotation.Nonnegative;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "books")
public class Book {
    public static final int DESCRIPTION_LENGTH = 500;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer id;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private String genre;

    @Column
    private String description;
    @Column
    private int quantity;
    @Column
    private int price;

    @Override
    public String toString(){
        return "id:" + this.getId() + " " +
                "title:" + this.getTitle() + " " +
                "author:" + this.getAuthor() + " " +
                "quantity:" + this.getQuantity() + " " +
                "price:" + this.getPrice() + " " +
                "genre:" + this.getGenre();
    }

    public Book(){}

    public Book(String title, String author, String genre, String description, int quantity, int price) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
