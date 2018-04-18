package main.service;

import main.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> findAll();
    public Optional<User> findById(Integer id);
    public void save(User user);
    public void delete(User user);
    public void deleteById(Integer id);
}
