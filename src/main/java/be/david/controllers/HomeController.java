package be.david.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by David on 16/09/2016.
 */
@Controller
@RequestMapping({"/","/index"})
public class HomeController {
    @RequestMapping(method = RequestMethod.GET)

    public String index() {
        return "index";
    }
}
