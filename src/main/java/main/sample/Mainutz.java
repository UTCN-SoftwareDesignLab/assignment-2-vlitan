package main.sample;

import main.model.Book;
import main.model.builder.BookBuilder;
import main.service.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Mainutz {
    public static void main(String[] args){
        ReportServiceFactory reportServiceFactory = new ReportServiceFactory();
       Book book1 = BookBuilder.aBook()
               .withAuthor("enescu")
               .withDescription("deosebit")
               .withGenre("combo")
               .withId(new Integer(1))
               .withQuantity(2)
               .withTitle("muzica sferelor")
               .withPrice(23)
               .build();
        Book book2 = BookBuilder.aBook()
                .withAuthor("HPLovecraft")
                .withDescription("mindly")
                .withGenre("fiction")
                .withId(new Integer(2))
                .withQuantity(3)
                .withTitle("crafting love")
                .withPrice(2)
                .build();
        List<Book> books = null;
        try {
            books = (new GoogleRecomandationService()).recomendByTitle("car").stream().map(i -> BookMapper.from(i)).collect(Collectors.toList());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReportService reportService = reportServiceFactory.getReportService(ReportType.CSV).get();
        try{
            reportService.generateReport("first", books);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
