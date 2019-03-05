package com.urjc.daw.api_rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.urjc.daw.models.user.User;
import com.urjc.daw.models.user.UserComponent;
import com.urjc.daw.models.user.UserRepository;
import com.urjc.daw.models.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRest {

    @Autowired
    UserComponent userComponent;
    @Autowired
    UserService userService;

    @PostMapping(value = "/user/")
    @ResponseStatus(HttpStatus.CREATED)
    public User add_new_user(@RequestBody User user) {
        userService.addUser(user);
        return user;
    }

    @GetMapping(value = "/user/{id}")
    public User getUser(@PathVariable long id) {
        User user = userService.findById(id).get();
        System.out.println(user.getName());
        return user;
    }

}
