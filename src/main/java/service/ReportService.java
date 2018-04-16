package service;

import model.Book;

import java.util.List;

public interface ReportService {
    /*creates a report with a list of books in the given directory*/
    public void generateReport(String path, String name, List<Book> books);
    /*creates a report with a list of books in the current directory*/
    public void generateReport(String name, List<Book> books);
}
