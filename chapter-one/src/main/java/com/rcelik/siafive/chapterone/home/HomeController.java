package com.rcelik.siafive.chapterone.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // http get handler for context path
    @GetMapping("/")
    public String welcome(){
        // render the template home.html in the src/main/resources/templates
        return "home";
    }

}
