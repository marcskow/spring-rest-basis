package pl.edu.agh.marcskow.spike.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.marcskow.spike.entities.UserData;
import pl.edu.agh.marcskow.spike.entities.UserSpike;
import pl.edu.agh.marcskow.spike.model.ErrorData;
import pl.edu.agh.marcskow.spike.model.UserDataModel;
import pl.edu.agh.marcskow.spike.services.interfaces.UserService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(
            value = "/users/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUserById(@PathVariable(value = "id")long id) {
        Optional<UserSpike> optionalUser = userService.findUserById(id);
        if(optionalUser.isPresent()) {
            return new ResponseEntity<>(new UserDataModel(optionalUser.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(
            value = "/users",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(Long id) {
        Optional<UserSpike> user = userService.findUserById(id);
        if (user.isPresent()) {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "users/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editUser(@PathVariable(value = "id")long id, @Valid @RequestBody UserDataModel userDataModel, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(new ErrorData(errors.toString()), HttpStatus.BAD_REQUEST);
        }
        Optional<UserSpike> optionalUser = userService.findUserById(id);
        if (optionalUser.isPresent()) {
            UserSpike user = optionalUser.get();
            Optional<UserDataModel> optionalUserDataDTO = userService.editUser(user, userDataModel);
            if (optionalUserDataDTO.isPresent())
                return new ResponseEntity<>(optionalUserDataDTO.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>(new ErrorData("Email not unique."), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new ErrorData("User with such id not found."), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/users",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDataModel userDataModel, String password, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(new ErrorData(errors.toString()), HttpStatus.BAD_REQUEST);
        }
        UserData personalData = new UserData(userDataModel);
        UserSpike user = new UserSpike(personalData, userDataModel.getUsername(), password);
        if (userService.addUser(user)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new ErrorData("User exists"), HttpStatus.BAD_REQUEST);
        }
    }

}

