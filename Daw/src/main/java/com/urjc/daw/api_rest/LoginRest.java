package com.urjc.daw.api_rest;


import com.urjc.daw.models.user.User;
import com.urjc.daw.models.user.UserComponent;
import com.urjc.daw.security.AuthenticationProviderUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/api")
public class LoginRest {
    private static final Logger log = LoggerFactory.getLogger(LoginRest.class);


    @Autowired
    private UserComponent userComponent;

    @Autowired
    private AuthenticationProviderUser authenticationProviderUser;
    private Authentication authentication;

    @RequestMapping("/logIn")
    public ResponseEntity<User> logIn (){
        if(!userComponent.isLoggedUser()){
            log.info("No user logged");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            User logged = userComponent.getLoggedUser();
            log.info("Logged as" + logged.getName());
            return new ResponseEntity<>(logged, HttpStatus.OK);
        }
    }

    @RequestMapping ("/logOut")
    public ResponseEntity<Boolean> logOut (HttpSession session) {

        if (!userComponent.isLoggedUser()) {
            log.info("No user logged");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            session.invalidate();
            log.info("Logged out");
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
    }

    @RequestMapping ("/signIn")
    public ResponseEntity<Boolean> signIn (){
       authenticationProviderUser.authenticate(authentication);
       return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
