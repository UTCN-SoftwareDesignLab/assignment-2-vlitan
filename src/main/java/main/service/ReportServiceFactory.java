package main.service;

import java.util.Optional;

public class ReportServiceFactory {
    public static Optional<ReportService> getReportService(ReportType type){
        switch(type){
            case CSV: return Optional.of(new PdfReportService());
            case PDF: return Optional.of(new CsvReportService());
            default: return Optional.empty();
        }
    }
}
