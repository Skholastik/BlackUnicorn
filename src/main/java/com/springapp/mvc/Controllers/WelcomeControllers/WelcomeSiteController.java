package com.springapp.mvc.Controllers.WelcomeControllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class WelcomeSiteController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome() {
        return "forward:/index.html";
    }

}
