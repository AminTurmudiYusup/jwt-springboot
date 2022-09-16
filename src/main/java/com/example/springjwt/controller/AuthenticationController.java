package com.example.springjwt.controller;

import com.example.springjwt.dto.AuthenticationRequest;
import com.example.springjwt.dto.AuthenticationResponse;
import com.example.springjwt.services.JwtTokenService;
import com.example.springjwt.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired//inject needed dependency using @Autowired or constructor
    private JwtTokenService jwtTokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtUserDetailsService jwtUserDetailsService) {//constructor
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailsService=jwtUserDetailsService;
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody @Valid final AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
        } catch (final BadCredentialsException badCredentialsException) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Username or Password", badCredentialsException);
        }
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUserName());
        final AuthenticationResponse authenticationResponse=new AuthenticationResponse();
        authenticationResponse.setAccessToken(jwtTokenService.generateToken(userDetails));
        return authenticationResponse;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello user";
    }
}
