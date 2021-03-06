package main.service;

import main.model.Book;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvReportService implements ReportService {
    @Override
    public void generateReport(String name, List<Book> books) throws IOException {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(name+this.getFileExtension()));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("id", "title", "author", "genre", "price"))
        ) {
            for (Book book : books){
                csvPrinter.printRecord(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getPrice());
            }

            csvPrinter.flush();
        }
    }

    @Override
    public String getFileExtension() {
        return ".csv";
    }
}
