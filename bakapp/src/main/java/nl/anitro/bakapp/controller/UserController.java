package nl.anitro.bakapp.controller;

import nl.anitro.bakapp.domain.BakappException;
import nl.anitro.bakapp.domain.User;
import nl.anitro.bakapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public ResponseEntity allUsers(){
        return new ResponseEntity(userService.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value="/users/{username}", method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable String username) {
        try {
            return new ResponseEntity(userService.getUserByName(username), HttpStatus.OK);
        } catch (BakappException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/users", method = RequestMethod.POST)
    public ResponseEntity registerUser(@RequestBody User user){
        try {
            return new ResponseEntity(userService.registerUser(user), HttpStatus.ACCEPTED);
        } catch (BakappException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
