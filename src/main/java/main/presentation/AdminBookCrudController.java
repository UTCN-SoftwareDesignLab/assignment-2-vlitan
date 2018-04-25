package main.presentation;

import main.model.Book;
import main.model.Role;
import main.model.User;
import main.model.builder.BookBuilder;
import main.model.builder.UserBuilder;
import main.service.AuthenticationServiceImpl;
import main.service.BookService;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class AdminBookCrudController {
    @Autowired
    BookService bookService;

    @RequestMapping(value = "admin_books", method = RequestMethod.GET)
    public String index(Model model, HttpSession httpSession){
        if (Role.valueOf(httpSession.getAttribute("userRole").toString()) == Role.ADMIN) {
            model.addAttribute("book", new Book());
            return "admin_books";
        }
        else{
            return "redirect:/login";
        }
    }


    @RequestMapping(value = "/adminBooks", method = RequestMethod.POST, params = "action=save")
    public String saveBook(@Validated @ModelAttribute("book") Book book, BindingResult bindingResult, Model model)
    {
        if (!bindingResult.hasErrors()){
            bookService.save(book);
            updateBookList(model);
        }
        return "admin_books";
    }
    @RequestMapping(value = "/adminBooks", method = RequestMethod.POST, params = "action=delete")
    public String deleteBook(@RequestParam("id") Integer id, Model model)
    {
        bookService.deleteById(id);
        updateBookList(model);
        model.addAttribute("book", new Book());
        return "admin_books";
    }
    @RequestMapping(value = "/adminBooks", method = RequestMethod.POST, params = "action=findAll")
    public String findAll(Model model)
    {
        updateBookList(model);
        model.addAttribute("book", new Book());
        return "admin_books";
    }

    private void updateBookList(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("bookList", books);
    }


}
