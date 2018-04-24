import main.model.Book;
import main.model.builder.BookBuilder;
import main.repository.BookRepository;
import main.service.BookService;
import main.service.BookServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService = new BookServiceImpl();

    @Test
    public void testFindById(){
        when(bookRepository.findById(1)).thenReturn(Optional.of(BookBuilder.aBook().withId(new Integer(1)).build()));

       Assert.assertEquals(bookRepository.findById(1).get().getId().intValue(), 1);
    }

}
