package main.presentation;

import main.model.Role;
import main.model.User;
import main.model.builder.UserBuilder;
import main.model.validator.UserValidator;
import main.service.AuthenticationServiceImpl;
import main.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminUserCrudController {
    @Autowired
    UserService userService;
    @Autowired
    UserValidator userValidator;
    @RequestMapping(value = "/crudUsers", method = RequestMethod.POST, params = "action=save")
    public ModelAndView saveUser(@RequestParam("id") String inId,
                                 @RequestParam("name") String name,
                                 @RequestParam("password") String password,
                                 @RequestParam("age") String inAge,
                                 @RequestParam("role") String inRole,
                                 Principal principal,
                                 BindingResult bindingResult)
    {
        int id;
        int age;
        Role role;
        id = Integer.parseInt(inId);
        age = Integer.parseInt(inAge);
        role = Role.valueOf(inRole);
        //TODO handle parse errors
        User user = UserBuilder.anUser()
                .withId(id)
                .withAge(age)
                .withName(name)
                .withPassword(password)
                .withRole(role)
                .build();
        userValidator.validate(user, bindingResult);
//        if (bindingResult.hasErrors()){
//            throw new Error(bindingResult.getAllErrors().stream().map(e -> e.toString()).collect(Collectors.joining("\n")));
//        }
//        else {
//            userService.save(user);
//        }
        return findAll(principal);
    }
    @RequestMapping(value = "/crudUsers", method = RequestMethod.POST, params = "action=delete")
    public ModelAndView deleteUser(@RequestParam("id") String inId, Principal principal)
    {
        int id;
        id = Integer.parseInt(inId);        //TODO handle parse errors
        userService.deleteById(id);
        return findAll(principal);
    }
    @RequestMapping(value = "/crudUsers", method = RequestMethod.POST, params = "action=findAll")
    public ModelAndView findAll(Principal principal)
    {
        List<User> users = userService.findAll();
        ModelAndView mav = new ModelAndView("crud_user");
        mav.addObject("userList", users);
        return mav;
    }


    // Specify name of a specific view that will be used to display the error:
    @ExceptionHandler({Exception.class})
    public ModelAndView genericExceptionHandler(HttpServletRequest req, Exception ex) {
        System.out.println("catched the error req" + req + "\t ex" + ex + "\t" + ex.getMessage());
        List<User> users = userService.findAll();
        ModelAndView mav = new ModelAndView("crud_user");
        mav.addObject("errorList", new ArrayList<String>(Arrays.asList(ex.getMessage())));
        return mav;
    }

}
