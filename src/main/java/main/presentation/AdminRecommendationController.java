package main.presentation;

import com.google.api.services.books.model.Volume;
import main.model.Book;
import main.model.Role;
import main.service.BookMapper;
import main.service.BookService;
import main.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminRecommendationController {//todo rethink to do this

    @Autowired
    BookService bookService;

    @Autowired
    RecommendationService<Volume> recommendationService;

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
    public String addBook(@RequestParam("index") Integer index,
                          @RequestParam("price") Integer price,
                          @RequestParam("quantity") Integer quantity,
                          Model model) {
        List<String> errors = new ArrayList<>();
        Book selectedBook = null;
        if (index == null || price == null || quantity == null){
            errors.add("Please fill in all the values!");
        }
        else {
            try {
                selectedBook = recommendationService.recommendByTitle(currentTitle).stream().map(BookMapper::from).collect(Collectors.toList()).get(index);
            } catch (GeneralSecurityException e) {
                errors.add("General security exception while fetching data\n");
            } catch (IOException e) {
                errors.add("IO exception exception while fetching data\n");
            }
            if (errors.isEmpty()) {
                selectedBook.setQuantity(quantity);
                selectedBook.setPrice(price);
                try {
                    bookService.save(selectedBook);
                } catch (Exception e) {
                    errors.add("Error while saving the book into the DB\n");
                }
            }
        }
        model.addAttribute("message", errors.stream().collect(Collectors.joining("\n")));
        return "book_recommendation";
    }

    @RequestMapping(value = "/recommendation", method = RequestMethod.POST, params = "action=recommend")
    public String recommendBooks(@RequestParam("title") String title, Model model, HttpSession httpSession) {
        List<Book> recommendedBooks = null;
        List<String> errors = new ArrayList<>();
        try {
            recommendedBooks = recommendationService.recommendByTitle(title).stream().map(BookMapper::from).collect(Collectors.toList());
            currentTitle = title;
        } catch (GeneralSecurityException e) {
            errors.add("General security exception while fetching data\n");
        } catch (IOException e) {
            errors.add("IO exception exception while fetching data\n");
        }
        if (errors.isEmpty()) {
            setTemporaryIndex(recommendedBooks);
            model.addAttribute("bookList", recommendedBooks);
        }
        else{
            model.addAttribute("message", errors.stream().collect(Collectors.joining("\n")));
        }

        return "book_recommendation";
    }

    private List<Book> setTemporaryIndex(List<Book> books) {//TODO refactor this. use iterators or something safer
        List<Book> newList = new ArrayList<>(books);
        for (int i = 0; i < newList.size(); i++) {
            newList.get(i).setId(new Integer(i));
        }
        return newList;
    }

}
