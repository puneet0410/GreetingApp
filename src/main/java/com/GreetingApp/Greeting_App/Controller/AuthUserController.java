package com.GreetingApp.Greeting_App.Controller;

import com.GreetingApp.Greeting_App.DTO.AuthUserDTO;
import com.GreetingApp.Greeting_App.DTO.LoginRequestDTO;
import com.GreetingApp.Greeting_App.DTO.LoginResponseDTO;
import com.GreetingApp.Greeting_App.Service.AuthenticationService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthUserController {


    private final AuthenticationService authenticationService;

    @Autowired
    public AuthUserController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AuthUserDTO authUserDTO) {
        authenticationService.registerUser(authUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO loginRequestDTO) throws MessagingException {
        LoginResponseDTO response = authenticationService.loginUser(loginRequestDTO);
        return ResponseEntity.ok(response);
    }
}
