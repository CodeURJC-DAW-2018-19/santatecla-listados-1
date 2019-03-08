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
@RequestMapping("/api/user")
public class UserRest {

    @Autowired
    UserComponent userComponent;
    @Autowired
    UserService userService;


    @GetMapping(value="/login")
    public ResponseEntity<User> logIn() {
        if (userComponent.getLoggedUser() != null){
            return new ResponseEntity<>(userComponent.getLoggedUser(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping(value="/register")
    public ResponseEntity<User> register(@RequestBody User newUser) {
        if(newUser == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User findUser = userService.findUserByName(newUser.getName());

        //If the user is already sign in or already exists
        if((userComponent.getLoggedUser() != null || findUser != null)){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User user = new User();
        user.setName(newUser.getName());
        user.setPassword(newUser.getPassword());
        userService.addUser(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public User add_new_user(@RequestBody User user) {
        userService.addUser(user);
        return user;
    }

    @GetMapping(value = "/{name}")
    public User getUser(@PathVariable String name) {
        return userService.findUserByName(name);
    }

}
