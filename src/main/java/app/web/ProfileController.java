package app.web;


import app.daos.UserDAO;
import app.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/users")
public class ProfileController
{
    private UserDAO userDAO;


    @Inject
    public ProfileController(UserDAO userDAO)
    {
        this.userDAO = userDAO;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showUsers(Model model)
    {
        List<String> users = userDAO.getAllUsernames();
        model.addAttribute("usersList",users);

        return "users";
    }

    @RequestMapping(path = "/{username}",method = RequestMethod.GET)
    public String user(@PathVariable String username,Model model)
    {
        if(!model.containsAttribute("user"))
        {
            User user = userDAO.getUserByUsername(username).get();
            model.addAttribute("user",user);
        }

        return "profile";
    }

    @RequestMapping(path = "/{username}",method = RequestMethod.POST)
    public String userApply(User user,@PathVariable String username,Model model)
    {

        userDAO.updateUser(user);
        model.addAttribute("user",user);

        return "profile";
    }

    @RequestMapping(path = "/{username}/settings",method = RequestMethod.GET)
    public String showSettingsForm(@PathVariable String username,Model model)
    {
        User user = userDAO.getUserByUsername(username).get();
        model.addAttribute("user",user);
        return "settings";
    }

    @RequestMapping(path = "/{username}/settings",method = RequestMethod.POST)
    public String applySettings(@PathVariable String username,User user,RedirectAttributes model)
    {


        User userUpdate = userDAO.getUserByUsername(username).get();
        userUpdate.setDetails(user.getDetails());
        userUpdate.setFirstName(user.getFirstName());
        userUpdate.setLastName(user.getLastName());

        userDAO.updateUser(userUpdate);
        model.addFlashAttribute("user",userUpdate);
        model.addAttribute("username",userUpdate.getUsername());
        return "redirect:/users/{username}";
    }

    @RequestMapping(path = "/{username}/remove",method = RequestMethod.GET)
    public String removeUser(@PathVariable String username)
    {
        userDAO.removeUserByUsername(username);
        return "redirect:/users";
    }

}
