package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.model.UserDto;
import web.model.UserDtoOutput;
import web.service.UserService;
import web.utils.ControllerHandler;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public void addUser(@RequestBody UserDto userDto){
        ControllerHandler.handleException(() -> userService.addUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username){
        return new ResponseEntity<>(userService.getUser(username), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDtoOutput> login(@RequestBody UserDto userDto) throws Exception {
        return ControllerHandler.handleException(() -> userService.getUserByUsernameAndPassword(userDto), HttpStatus.OK);
    }
}
