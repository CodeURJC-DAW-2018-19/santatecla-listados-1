package com.urjc.daw.api_rest;

import com.urjc.daw.models.user.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRest {

    UserService userService;
}
