package main.presentation;

import main.model.Role;
import main.model.User;
import main.model.builder.UserBuilder;
import main.model.validator.UserValidator;
import main.service.AuthenticationService;
import main.service.AuthenticationServiceImpl;
import main.service.UserService;
import main.util.Notification;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.tomcat.jni.Error;
import org.omg.CORBA.ExceptionList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Nonnegative;
import java.util.List;

@Controller
public class AdminUserCrudController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "crud_user", method = RequestMethod.GET)
    public String index(Model model) {
        System.out.println("indexed");
        model.addAttribute("user", new User());
        return "crud_user";
    }

    @RequestMapping(value = "/crudUsers", method = RequestMethod.POST, params = "action=save")
    public String saveUser(@Validated @ModelAttribute("user") User user, BindingResult bindingResult, Model model)
    {
        if (!bindingResult.hasErrors()){
            Notification<Boolean> saveNotification = authenticationService.register(user);
            if (saveNotification.hasErrors()){
                model.addAttribute("message", saveNotification.getFormattedErrors());
            }
            updateUsersList(model);
        }
        return "crud_user";
    }
    @RequestMapping(value = "/crudUsers", method = RequestMethod.POST, params = "action=delete")
    public String deleteUser(@RequestParam("id") Integer id, Model model)
    {
        Notification<Boolean> deleteNotification = userService.deleteById(id);
        if (deleteNotification.hasErrors()){
            model.addAttribute("message", deleteNotification.getFormattedErrors());
        }
        updateUsersList(model);
        model.addAttribute("user", new User());
        return "crud_user";
    }
    @RequestMapping(value = "/crudUsers", method = RequestMethod.POST, params = "action=findAll")
    public String findAll(Model model)
    {
        updateUsersList(model);
        model.addAttribute("user", new User());
        return "crud_user";
    }

    private void updateUsersList(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("userList", users);
    }

}
