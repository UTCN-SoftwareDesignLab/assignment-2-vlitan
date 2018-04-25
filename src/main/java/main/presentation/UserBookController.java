package main.presentation;

import main.model.Book;
import main.service.BookService;
import main.util.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class UserBookController {
    @Autowired
    BookService bookService;

    @RequestMapping(value = "user_books", method = RequestMethod.GET)
    public String index(Model model){
        return "user_books";
    }

    @RequestMapping(value = "/userBooks", method = RequestMethod.POST, params = "action=searchByGenre")
    public ModelAndView listByGenre(@RequestParam("genre") String genre, Principal principal)
    {
        List<Book> bookList = bookService.findByGenre(genre);
        ModelAndView mav = new ModelAndView("user_books");
        mav.addObject("bookList", bookList);
        return mav;
    }
    @RequestMapping(value = "/books", method = RequestMethod.POST, params = "action=searchByAuthor")
    public ModelAndView listByAuthor(@RequestParam("author") String author, Principal principal)
    {
        List<Book> bookList = bookService.findByAuthor(author);
        ModelAndView mav = new ModelAndView("user_books");
        mav.addObject("bookList", bookList);
        return mav;
    }
    @RequestMapping(value = "/books", method = RequestMethod.POST, params = "action=searchByTitle")
    public ModelAndView listByTitle(@RequestParam("title") String title, Principal principal) {
        List<Book> bookList = bookService.findByTitle(title);
        ModelAndView mav = new ModelAndView("user_books");
        mav.addObject("bookList", bookList);
        return mav;
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST, params = "action=findAll")
    public ModelAndView listAll(Principal principal) {
        List<Book> bookList = bookService.findAll();
        ModelAndView mav = new ModelAndView("user_books");
        mav.addObject("bookList", bookList);
        return mav;
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST, params = "action=sellSelected")
    public ModelAndView sellById(@RequestParam("id") String inId, Principal principal)
    {
        int id = -1;
        String formtedErrors = "";
        Notification<Boolean> sellNotification;
        try{
            id = Integer.parseInt(inId);
        }
        catch (Exception e){
            formtedErrors += "Cannot convert " + inId + " to an integer\n";
        }
        if (formtedErrors.isEmpty()) {
            sellNotification = bookService.sellById(id);
            if (sellNotification.hasErrors()) {
                formtedErrors += sellNotification.getFormattedErrors();
            }
        }

        if (!formtedErrors.isEmpty()){
            System.out.println("errors to be displayed in gui:\n" + formtedErrors); //TODO display errors properly
        }
     //   ModelAndView mav = new ModelAndView("user_books");
        return listAll(principal);//TODO is this ok?? rethink this.. I think it is not ok
        //it should be fixed in a spring-ie way
    }
}
