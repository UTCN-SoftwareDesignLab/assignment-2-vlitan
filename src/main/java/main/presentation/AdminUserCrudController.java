package main.presentation;

import main.model.Role;
import main.model.User;
import main.model.builder.UserBuilder;
import main.service.AuthenticationServiceImpl;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class AdminUserCrudController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/crudUsers", method = RequestMethod.POST, params = "action=save")
    public ModelAndView saveUser(@RequestParam("id") String inId,
                                   @RequestParam("name") String name,
                                   @RequestParam("password") String password,
                                   @RequestParam("age") String inAge,
                                   @RequestParam("role") String inRole,
                                   Principal principal)
    {
        int id;
        int age;
        Role role;
        id = Integer.parseInt(inId);
        age = Integer.parseInt(inAge);
        role = Role.valueOf(inRole);
        //TODO handle parse errors
        userService.save(UserBuilder.anUser()
        .withId(id)
        .withAge(age)
        .withName(name)
        .withPassword(AuthenticationServiceImpl.encodePassword(password))//TODO rethink password encoding and validation
        .withRole(role)
        .build());
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

}
