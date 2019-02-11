package com.urjc.daw.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping(path = "/addUser")
    public void showUser(@RequestParam String name,@RequestParam String email){
        System.out.println(name);
        System.out.println(email);
    }

}
