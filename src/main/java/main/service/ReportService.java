package main.service;

import main.model.Book;

import java.io.IOException;
import java.util.List;

public interface ReportService{
    /*creates a report with a list of books*/
    public void generateReport(String name, List<Book> books)  throws IOException;
}
