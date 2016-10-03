package app.web;


import app.daos.UserDAO;
import app.daos.UserRepository;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController
{
    private UserDAO userDao;

    @Inject
    public RegisterController(UserDAO userDao)
    {
        this.userDao = userDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showFrom(Model model)
    {
        model.addAttribute("user",new User());
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processRegistration(@Valid User user,Errors errors, RedirectAttributes model)
    {
        if(errors.hasErrors())
        {
            return "register";
        }


        if(!userDao.addUserIfNotExists(user))
        {
            errors.rejectValue("username",user.getUsername(),"User with this username exists");
            return "register";
        }


        model.addAttribute("username",user.getUsername());
        model.addFlashAttribute("user",user);
        return "redirect:/users/{username}";
    }
}
