package main.service;

import com.google.api.services.books.model.Volume;
import main.model.Book;
import main.model.builder.BookBuilder;

public final class BookMapper {
    public static Book from(Volume volume){
        return BookBuilder.aBook()
                .withAuthor(volume.getVolumeInfo().getAuthors().get(0).toString())//TODO add all authors by using streams
                .withTitle(volume.getVolumeInfo().getTitle())
                .withDescription(volume.getVolumeInfo().getDescription())
                .withGenre(volume.getVolumeInfo().getMainCategory())
                .withPrice(volume.getSaleInfo().getRetailPrice().getAmount().intValue())//TODO revise this
                .build();
    }
}
