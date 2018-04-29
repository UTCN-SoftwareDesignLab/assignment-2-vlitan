package main.presentation;

import main.model.Book;
import main.service.BookService;
import main.service.SellingService;
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
    @Autowired
    SellingService sellingService;

    @RequestMapping(value = "user_books", method = RequestMethod.GET)
    public String index(Model model){
        return "user_books";
    }

    @RequestMapping(value = "/userBooks", method = RequestMethod.POST, params = "action=searchByGenre")
    public String listByGenre(@RequestParam("genre") String genre, Model model) {
        updateBookList(model, bookService.findByGenre(genre));
        return "user_books";
    }
    @RequestMapping(value = "/userBooks", method = RequestMethod.POST, params = "action=searchByAuthor")
    public String listByAuthor(@RequestParam("author") String author, Model model) {
        updateBookList(model, bookService.findByAuthor(author));
        return "user_books";
    }
    @RequestMapping(value = "/userBooks", method = RequestMethod.POST, params = "action=searchByTitle")
    public String listByTitle(@RequestParam("title") String title, Model model) {
        updateBookList(model, bookService.findByTitle(title));
        return "user_books";
    }

    @RequestMapping(value = "/userBooks", method = RequestMethod.POST, params = "action=findAll")
    public String listAll(Model model) {
        updateBookList(model, bookService.findAll());
        return "user_books";
    }

    private void updateBookList(Model model, List<Book> bookList) {
        model.addAttribute("bookList", bookList);
    }

    @RequestMapping(value = "/userBooks", method = RequestMethod.POST, params = "action=sellSelected")
    public String sellById(@RequestParam("id") Integer id, Model model)
    {
        Notification<Boolean> sellNotification = new Notification<Boolean>();
        if (id != null) {
            sellNotification = sellingService.sellById(id, 1);
        }
        else{
            sellNotification.addError("id is null");
        }
        if (sellNotification.hasErrors()) {
            model.addAttribute("message", sellNotification.getFormattedErrors());
        }
        updateBookList(model, bookService.findAll());
        return "user_books";
    }
}
