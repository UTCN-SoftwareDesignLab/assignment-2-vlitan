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
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class AdminReportController {
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
    public String login(@RequestParam("path") String path, @RequestParam("type") String type, Model model,
                        HttpSession httpSession,
                        HttpServletResponse httpServletResponse) {
        ReportType reportType = ReportType.NONE;
        List<String> errors = new ArrayList<>();
        try{
            reportType = ReportType.valueOf(type);
        }
        catch (Exception e){
            errors.add("No such report type exists\n");
        }
        Optional<ReportService> reportService = reportServiceFactory.getReportService(reportType);
        if (reportService.isPresent()){
            try {
                reportService.get().generateReport(path, bookService.findByQuantity(0));

            } catch (IOException e) {
                errors.add("Error while opening or creating the file\n");
            }
            try {
                provideDownloadable(path, httpServletResponse, reportService);
            } catch (IOException e) {
                errors.add("error while creating downloadable file");
            }
        }
        else{
            errors.add("No such service available\n");
        }

        if (errors.isEmpty()){
            model.addAttribute("message", "Report created!\n");
        }
        else{
            model.addAttribute("message", "Report not created! Something went wrong:\n" + errors.stream().collect(Collectors.joining("\n")));
        }
        return "admin_reports";
    }

    private void provideDownloadable(@RequestParam("path") String path, HttpServletResponse response, Optional<ReportService> reportService) throws IOException {
        File file = new File(path + reportService.get().getFileExtension());
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());

        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        outStream.flush();
        inStream.close();
    }

}
