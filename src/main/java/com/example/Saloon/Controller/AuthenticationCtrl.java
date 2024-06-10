package com.example.Saloon.Controller;

import com.example.Saloon.Dto.LoginDto;
import com.example.Saloon.Dto.NewUserDto;
import com.example.Saloon.Logging.LoginMessage;
import com.example.Saloon.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthenticationCtrl {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<String> register( @RequestBody NewUserDto newUser) {
        try {
            return new ResponseEntity<String>(userService.signUp(newUser), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("Registration Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginDto loginDto){

        LoginMessage loginMessage=  userService.login(loginDto);
        return  ResponseEntity.ok(loginMessage);

    }
}
