package com.sinue.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sinue.auth.dto.requets.AuthRequestDTO;
import com.sinue.auth.dto.response.AuthResponseDTO;
import com.sinue.auth.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    //REGISTER
    @PostMapping("/register")
    public AuthResponseDTO register(@RequestBody AuthRequestDTO request) {
        String msg = authService.register(request);
        return new AuthResponseDTO(msg);
    }

    //LOGIN
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        String token = authService.login(request);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        return new ResponseEntity<>(new AuthResponseDTO(token), headers, HttpStatus.OK);
    }
}