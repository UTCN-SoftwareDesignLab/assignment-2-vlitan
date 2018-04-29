package main.service;

import main.model.User;
import main.repository.UserRepository;
import main.util.Notification;
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
    public Notification<Boolean> save(User user) {//TODO extract register from this into AuthenticationService
        Notification<Boolean> saveNotification = new Notification<>();
        String plainPassword = user.getPassword();
        user.setPassword(AuthenticationServiceImpl.encodePassword(plainPassword));
        if (userRepository.findByNameAndPassword(user.getName(), user.getPassword()).isPresent()){
            saveNotification.addError("Already existing user with name " + user.getName() + " and same password");
            saveNotification.setResult(Boolean.FALSE);
        }
        else {
            try {
                userRepository.save(user);
                saveNotification.setResult(Boolean.TRUE);
            }
            catch (Exception e){
                saveNotification.addError("Something went bad while saving");
                saveNotification.setResult(Boolean.FALSE);
            }
        }
        user.setPassword(plainPassword);
        return saveNotification;
    }


    @Override
    public Notification<Boolean> deleteById(Integer id) {
        Notification<Boolean> deleteNotification = new Notification<>();
        if (id.intValue()>0){
            try{
                userRepository.deleteById(id);
                deleteNotification.setResult(Boolean.TRUE);
            }
            catch (Exception e){
                deleteNotification.setResult(Boolean.FALSE);
                deleteNotification.addError("Something went bad while deleting");
            }
        }
        else{
            deleteNotification.setResult(Boolean.TRUE);
            deleteNotification.addError("Id cannot be negative");
        }
        return deleteNotification;
    }
}
