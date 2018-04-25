package main.presentation;

import com.google.api.services.books.model.Volume;
import main.model.Book;
import main.model.Role;
import main.model.User;
import main.model.builder.BookBuilder;
import main.service.BookMapper;
import main.service.BookService;
import main.service.RecomandationService;
import main.util.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class AdminRecommandationController {//todo rethink to do this

    @Autowired
    BookService bookService;

    @Autowired
    RecomandationService<Volume> recommendationService;

    private String currentTitle;//TODO remove state

    @RequestMapping(value = "/book_recommendation", method = RequestMethod.GET)
    @Order(value = 1)
    public String index(HttpSession httpSession) {
        if (Role.valueOf(httpSession.getAttribute("userRole").toString()) == Role.ADMIN) {
            return "/book_recommendation";
        }
        else{
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/recommendation", method = RequestMethod.POST, params = "action=addSelected")
    public String addBook(@RequestParam("index") int index,
                          @RequestParam("price") int price,
                          @RequestParam("quantity") int quantity,
                          Model model) {
        Book selectedBook = null;
        try {
            selectedBook = recommendationService.recomendByTitle(currentTitle).stream().map(BookMapper::from).collect(Collectors.toList()).get(index);
        } catch (GeneralSecurityException e) {
            return "redirect:/recommendation?googleError";
        } catch (IOException e) {
           return "redirect:/recommendation?googleError";
        }
        selectedBook.setQuantity(quantity);
        selectedBook.setPrice(price);

        bookService.save(selectedBook);
        return "book_recommendation";
    }

    @RequestMapping(value = "/recommendation", method = RequestMethod.POST, params = "action=recommend")
    public ModelAndView recommendBooks(@RequestParam("title") String title, Model model, HttpSession httpSession) {
        List<Book> recommendedBooks = new ArrayList<>();
        try {
            recommendedBooks = recommendationService.recomendByTitle(title).stream().map(BookMapper::from).collect(Collectors.toList());
            currentTitle = title;
        } catch (GeneralSecurityException e) {
            return new ModelAndView("redirect:/recommendation?googleError");
        } catch (IOException e) {
            return new ModelAndView("redirect:/recommendation?googleError");
        }
        setTemporaryIndex(recommendedBooks);
        ModelAndView mav = new ModelAndView("book_recommendation");
        mav.addObject("bookList", recommendedBooks);
        return mav;
    }

    private List<Book> setTemporaryIndex(List<Book> books) {//TODO refactor this. use iterators or something safer
        List<Book> newList = new ArrayList<>(books);
        for (int i = 0; i < newList.size(); i++) {
            newList.get(i).setId(new Integer(i));
        }
        return newList;
    }

}
