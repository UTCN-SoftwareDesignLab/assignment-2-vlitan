package main.presentation;

import main.model.Book;
import main.model.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

//this class just redirects the admin to other pages.
//it could be more appropriate to use some HTML shenanigans in order to do this.. idk
@Controller
public class AdminController {

    @RequestMapping(value = "admin_view", method = RequestMethod.GET)
    public String index(HttpSession httpSession){
        if (Role.valueOf(httpSession.getAttribute("userRole").toString()) == Role.ADMIN) {
            return "admin_view";
        }
        else{
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/adminView", method = RequestMethod.POST, params = "redirect=toReports")
    public String switchToReports()
    {
        return "redirect:/admin_reports";
    }

    @RequestMapping(value = "/adminView", method = RequestMethod.POST, params = "redirect=toBookCrud")
    public String switchToBookCrud()
    {
        return"redirect:/admin_books";
    }

    @RequestMapping(value = "/adminView", method = RequestMethod.POST, params = "redirect=toUserCrud")
    public String switchToUserCrud()
    {
        return"redirect:/crud_user";
    }

    @RequestMapping(value = "/adminView", method = RequestMethod.POST, params = "redirect=toRecommendation")
    public String switchToRecommendation()
    {
        return "redirect:/book_recommendation";
    }
}
