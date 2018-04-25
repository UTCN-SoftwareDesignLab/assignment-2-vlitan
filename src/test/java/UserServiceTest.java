import main.model.User;
import main.model.builder.BookBuilder;
import main.model.builder.UserBuilder;
import main.repository.BookRepository;
import main.repository.UserRepository;
import main.service.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService = new UserServiceImpl();

    @Test
    public void testSave(){
        String encodedB = AuthenticationServiceImpl.encodePassword("B");
        User user = UserBuilder.anUser().withName("A").withPassword(encodedB).build();
        when(userRepository.findByNameAndPassword("A", encodedB)).thenReturn(Optional.of(user));
        user.setPassword("B");
        Assert.assertTrue(userService.save(user).hasErrors());
    }
}
