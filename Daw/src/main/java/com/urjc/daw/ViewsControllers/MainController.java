package com.urjc.daw.ViewsControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(path = "/")
    public String showIndex(Model model){
        return "index";
    }

    @GetMapping(path = "/mainPage")
    public String showPrueba(Model model){
        return "MainPage";
    }
}
