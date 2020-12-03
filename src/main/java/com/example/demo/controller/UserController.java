package com.example.demo.controller;

import com.example.demo.dto.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Validated
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @RequestMapping("/user/count")
    public ResponseEntity countUniqueUser(@RequestBody
                                          @NotEmpty(message = "User list can not be empty.")
                                                  List<@Valid User> users) {
        Integer count = userService.countUniqueUsers(users);
        return new ResponseEntity(count, HttpStatus.OK);

    }

    @PostMapping
    @RequestMapping("/user/update")
    public ResponseEntity getUpdatedUser(@RequestBody
                                         @NotEmpty(message = "User list can not be empty.")
                                                 List<@Valid User> users) {
        User user = userService.getUpdatedUser(users);
        return new ResponseEntity(user, HttpStatus.OK);

    }
}
