package main.presentation;

import com.google.api.services.books.model.Volume;
import main.model.Book;
import main.model.User;
import main.model.builder.BookBuilder;
import main.service.BookMapper;
import main.service.BookService;
import main.service.RecomandationService;
import main.util.Notification;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class AdminRecommandationController {

    @Autowired
    BookService bookService;

    @Autowired
    RecomandationService<Volume> recommendationService;

    private List<Book> recommendedBooks = new ArrayList<>();

    @RequestMapping(value = "/recommendation", method = RequestMethod.POST, params = "action=addSelected")
    public String addBook(@RequestParam("index") String inIndex,
                          @RequestParam("price") String inPrice,
                          @RequestParam("quantity") String inQuantity,
                          Model model, HttpSession httpSession) {
        int index;
        int price;
        int quantity;
        index = Integer.parseInt(inIndex);
        price = Integer.parseInt(inPrice);
        quantity = Integer.parseInt(inQuantity);
        //TODO manage parse errors
        Book selectedBook = recommendedBooks.get(index);
        selectedBook.setQuantity(quantity);
        selectedBook.setPrice(price);
        bookService.save(selectedBook);
        return "book_recommendation";
    }

    @RequestMapping(value = "/recommendation", method = RequestMethod.POST, params = "action=recommend")
    public ModelAndView recommendBooks(@RequestParam("title") String title, Model model, HttpSession httpSession) {
        try {
            List<Volume> volumes = recommendationService.recomendByTitle(title);
            recommendedBooks.clear();
            for (Volume volume : volumes){
                recommendedBooks.add(BookMapper.from(volume));
            }
            //recommendedBooks = recommendationService.recomendByTitle(title).stream().map(BookMapper::from).collect(Collectors.toList());
        } catch (GeneralSecurityException e) {//TODO handle errors
        //    e.printStackTrace();
        } catch (IOException e) {
          //  e.printStackTrace();
        }
        recommendedBooks = setTemporaryIndex(recommendedBooks);
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
