package com.springapp.mvc.Controllers.WelcomeControllers;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class WelcomeAdminController {


    @RequestMapping(path = {"/admin"}, method = RequestMethod.GET)
    public String firstLevelIndex() {
        return "forward:/index.html";
    }

    @RequestMapping(path = {"/admin/*"}, method = RequestMethod.GET)
    public String secondLevelIndex() {
        return "forward:/index.html";
    }

    @RequestMapping(path = {"/admin/*/*"}, method = RequestMethod.GET)
    public String thirdLevelIndex() {
        return "forward:/index.html";
    }

    @RequestMapping(path = {"/admin/*/*/*"}, method = RequestMethod.GET)
    public String fourthLevelIndex() {
        return "forward:/index.html";
    }

    @RequestMapping(path = {"/admin/*/*/*/*"}, method = RequestMethod.GET)
    public String fifthLevelIndex() {
        return "forward:/index.html";
    }




    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
