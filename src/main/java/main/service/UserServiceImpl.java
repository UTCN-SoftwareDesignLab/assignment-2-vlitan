package main.service;

import main.model.User;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        String plainPassword = user.getPassword();
        user.setPassword(AuthenticationServiceImpl.encodePassword(plainPassword));
        if (userRepository.findByNameAndPassword(user.getName(), user.getPassword()).isPresent()){
            //TODO handle "already existing" case
        }
        else {
            userRepository.save(user);
        }
        user.setPassword(plainPassword);
    }

    @Override
    public void delete(User user) {
    }

    @Override
    public void deleteById(Integer id) {
        if (id.intValue()>0){
            userRepository.deleteById(id);
        }
        else{
            //tODO manage error
        }
    }
}
