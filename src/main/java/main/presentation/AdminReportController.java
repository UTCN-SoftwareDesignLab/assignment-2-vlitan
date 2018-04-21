package main.presentation;

import main.service.ReportServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminReportController {
    @Autowired
    ReportServiceFactory reportServiceFactory;

}
