package main.presentation;

import main.model.Book;
import main.model.Role;
import main.service.BookService;
import main.service.ReportService;
import main.service.ReportServiceFactory;
import main.service.ReportType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Controller
public class AdminReportController {//TODO use an error List rather than concatenating strings like a caveman
    //TODO use a dropdown instead of a textBox for report type input
    @Autowired
    ReportServiceFactory reportServiceFactory;

    @Autowired
    BookService bookService;
    @RequestMapping(value = "admin_reports", method = RequestMethod.GET)
    public String index(Model model, HttpSession httpSession){
        if (Role.valueOf(httpSession.getAttribute("userRole").toString()) == Role.ADMIN) {
            return "admin_reports";
        }
        else{
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/adminReports", method = RequestMethod.POST, params = "action=generate")
    public String login(@RequestParam("path") String path, @RequestParam("type") String type, Model model, HttpSession httpSession) {
        ReportType reportType = ReportType.NONE;
        String formatedError = "";
        try{
            reportType = ReportType.valueOf(type);
        }
        catch (Exception e){
            formatedError += "no such report type exists\n";
        }
        Optional<ReportService> reportService = reportServiceFactory.getReportService(reportType);
        if (reportService.isPresent()){
            try {
                reportService.get().generateReport(path, bookService.findByQuantity(0));
            } catch (IOException e) {
                formatedError += "Error while opening or creating the file\n";
            }
        }
        else{
            formatedError += "No such service available\n";
        }

        if (formatedError.isEmpty()){
            System.out.println("[AdminReportController] report created!"); //TODO display this in GUI
        }
        else{
            System.out.println(formatedError); //TODO display this in GUI
        }
        return "admin_reports";
    }
}
