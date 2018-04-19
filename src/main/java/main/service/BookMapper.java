package main.service;

import com.google.api.services.books.model.Volume;
import main.model.Book;
import main.model.builder.BookBuilder;

import java.util.stream.Collectors;

public final class BookMapper {
    public static Book from(Volume volume){
        String authors = "";
        String genre = "";
        int price = 0;
        if (volume.getVolumeInfo().getAuthors() == null){
            authors = "unknown";
        }
        else{
            authors = volume.getVolumeInfo().getAuthors().stream().collect(Collectors.joining(", "));
        }
        if (volume.getVolumeInfo().getCategories() == null){
            genre = "unknown";
        }
        else{
            genre = volume.getVolumeInfo().getCategories().stream().collect(Collectors.joining(", "));
        }
        if (volume.getSaleInfo().getListPrice() == null){
            price = 0;
        }
        else {
            price = volume.getSaleInfo().getListPrice().getAmount().intValue();
        }
        return BookBuilder.aBook()
                .withAuthor(authors)
                .withTitle(volume.getVolumeInfo().getTitle())
                .withDescription(volume.getVolumeInfo().getDescription())
                .withGenre(genre)
                .withPrice(price)
                .build();
    }
}
