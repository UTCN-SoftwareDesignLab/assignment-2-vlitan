//package main.service;
//
//import main.model.User;
//import main.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service("userDetailsService")
//public class UserDetailServiceImpl implements UserDetailsService {
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
//        Optional<User> optionalUser = userRepository.findByName(username);
//        if (optionalUser.isPresent()){
//            User user = optionalUser.get();
//            user.setEnabled(true);
//            return buildUserForAuth(user);
//        }
//        else{
//            throw new UsernameNotFoundException(username + " not found");
//        }
//    }
//
//    private org.springframework.security.core.userdetails.User buildUserForAuth(User user){
//        return new org.springframework.security.core.userdetails.User(
//          user.getName(),
//          user.getPassword(),
//          user.isEnabled(),
//          true,true,true,
//                new ArrayList<GrantedAuthority>(Arrays.asList(new SimpleGrantedAuthority(user.getRole().toString())))
//        );
//    }
//
//}
