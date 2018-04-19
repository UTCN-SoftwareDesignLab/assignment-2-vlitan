package main.service;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportServiceFactory {
    public static Optional<ReportService> getReportService(ReportType type){
        switch(type){
            case CSV: return Optional.of(new CsvReportService());
            case PDF: return Optional.of(new PdfReportService());
            default: return Optional.empty();
        }
    }
}
