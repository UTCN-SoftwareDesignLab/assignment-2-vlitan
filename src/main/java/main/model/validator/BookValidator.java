package main.model.validator;

import main.model.Book;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


@Component
public class BookValidator implements org.springframework.validation.Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Book.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        titleValidator(book.getTitle(), errors);
        authorValidator(book.getAuthor(), errors);
        genreValidator(book.getGenre(), errors);
        descriptionValidator(book.getDescription(), errors);
        priceValidator(book.getPrice(), errors);
        quantityValidator(book.getQuantity(), errors);
    }

    private void titleValidator(String title, Errors errors){
        if (title == null){
            errors.rejectValue("title", "This field cannot be null");
        }
        else if (title.isEmpty()){
            errors.rejectValue("title", "This field cannot be empty");
        }
    }

    private void authorValidator(String author, Errors errors){
        if (author == null){
            errors.rejectValue("author", "This field cannot be null");
        }
        else if (author.isEmpty()){
            errors.rejectValue("author", "This field cannot be empty");
        }
    }

    private void genreValidator(String genre, Errors errors){
        //no restrictions
    }

    private void descriptionValidator(String description, Errors errors){
        //no restrictions
    }

    private void quantityValidator(int quantity, Errors errors){
        if (quantity < 0){
            errors.rejectValue("quantity", "This field cannot be negative");
        }
    }

    private void priceValidator(int price, Errors errors){
        if (price < 0){
            errors.rejectValue("price", "This field cannot be negative");
        }
    }

}
