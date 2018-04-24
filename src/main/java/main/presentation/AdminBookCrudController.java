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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class AdminBookCrudController {
    @Autowired
    BookService bookService;

    @RequestMapping(value = "/adminBooks", method = RequestMethod.POST, params = "action=save")
    public ModelAndView saveBook(@RequestParam("id") String inId,
                                 @RequestParam("author") String author,
                                 @RequestParam("title") String title,
                                 @RequestParam("genre") String genre,
                                 @RequestParam("price") String inPrice,
                                 @RequestParam("quantity") String inQuantity,

                                 Principal principal)
    {
        int id;
        int price;
        int quantity;

        id = Integer.parseInt(inId);
        price = Integer.parseInt(inPrice);
        quantity = Integer.parseInt(inQuantity);
        //TODO handle parse errors
        bookService.save(BookBuilder.aBook()
                                    .withAuthor(author)
                                    .withTitle(title)
                                    .withGenre(genre)
                                    .withPrice(price)
                                    .withQuantity(quantity)
                                    .withId(id)
                                    .build());
        return findAll(principal);
    }
    @RequestMapping(value = "/adminBooks", method = RequestMethod.POST, params = "action=delete")
    public ModelAndView deleteBook(@RequestParam("id") String inId, Principal principal)
    {
        int id;
        id = Integer.parseInt(inId);        //TODO handle parse errors
        bookService.deleteById(id);
        return findAll(principal);
    }
    @RequestMapping(value = "/adminBooks", method = RequestMethod.POST, params = "action=findAll")
    public ModelAndView findAll(Principal principal)
    {
        List<Book> books = bookService.findAll();
        ModelAndView mav = new ModelAndView("admin_books");
        mav.addObject("bookList", books);
        return mav;
    }

}
