package main.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

//this class just redirects the admin to other pages.
//it could be more appropriate to use some HTML shenanigans in order to do this.. idk
@Controller
public class AdminController {

    @RequestMapping(value = "/adminView", method = RequestMethod.POST, params = "redirect=toReports")
    public ModelAndView switchToReports(Principal principal)
    {
        return new ModelAndView("admin_reports");
    }

    @RequestMapping(value = "/adminView", method = RequestMethod.POST, params = "redirect=toBookCrud")
    public ModelAndView switchToBookCrud(Principal principal)
    {
        return new ModelAndView("admin_books");
    }

    @RequestMapping(value = "/adminView", method = RequestMethod.POST, params = "redirect=toUserCrud")
    public ModelAndView switchToUserCrud(Principal principal)
    {
        return new ModelAndView("crud_user");
    }

    @RequestMapping(value = "/adminView", method = RequestMethod.POST, params = "redirect=toRecommendation")
    public ModelAndView switchToRecommendation(Principal principal)
    {
        System.out.println("git gut");
        return new ModelAndView("book_recommendation");
    }
}
