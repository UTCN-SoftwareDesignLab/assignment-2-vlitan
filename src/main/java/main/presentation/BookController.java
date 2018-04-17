package main.presentation;

import main.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import main.service.BookService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping(value = "/{ff}", method = RequestMethod.GET)
    public ModelAndView listAll(@PathVariable("ff") String firstName, Principal principal)
    {
        System.out.println("[BookController] all request made");

        List<Book> bookList = bookService.findByTitle("how I got happy");
        System.out.println(bookList.size());
        ModelAndView mav = new ModelAndView("book_list");
        mav.addObject("bookList", bookList);
        return mav;
    }
}
