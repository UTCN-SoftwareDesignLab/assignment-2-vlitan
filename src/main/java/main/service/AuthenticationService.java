package main.service;

import main.model.User;
import main.repository.UserRepository;
import main.util.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

public interface AuthenticationService {
    public Notification<User> loadByNameAndPassword(String username, String password);
    Notification<Boolean> register(User user);
}
