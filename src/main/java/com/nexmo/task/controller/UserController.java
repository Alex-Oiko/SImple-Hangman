package com.nexmo.task.controller;


import com.nexmo.task.domain.AppUser;
import com.nexmo.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/create/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUser> create(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.createUser(username));
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppUser>> findAllUsers() {
        return ResponseEntity.ok().body(userService.findAllUsers());
    }
}
