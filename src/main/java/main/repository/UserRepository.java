package main.repository;

import main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Integer> {
    public Optional<User> findByName(String name);
    public Optional<User> findByNameAndPassword(String username, String password);
}
