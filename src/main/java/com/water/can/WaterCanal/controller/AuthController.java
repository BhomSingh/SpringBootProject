package com.water.can.WaterCanal.controller;

import com.water.can.WaterCanal.bean.*;
import com.water.can.WaterCanal.config.JwtTokenHelper;
import com.water.can.WaterCanal.config.JwtRequest;
import com.water.can.WaterCanal.config.JwtResponse;
import com.water.can.WaterCanal.service.UserAuth.AuthService;
import com.water.can.WaterCanal.model.Register;
import com.water.can.WaterCanal.service.UserAuth.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtTokenHelper helper;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<GenericResponse<RegisterBean>> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        RegisterBean registerBean = new RegisterBean();

        if (authService.isEmailAlreadyRegistered(signUpRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new GenericResponse<>(0, "Email is already in use!", registerBean));
        }

        if (authService.isMobileNoAlreadyRegistered(signUpRequest.getMobileNo())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new GenericResponse<>(0, "Mobile no is already in use!", registerBean));
        }

        Register registerObj = authService.save(signUpRequest);
        registerBean.setUserName(registerObj.getUsername());
        registerBean.setEmail(registerObj.getEmail());
        registerBean.setMobileNo(registerObj.getMobileNo());
        return ResponseEntity.ok(new GenericResponse<>(1, "Success", registerBean));
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse<JwtResponse>> login(@RequestBody JwtRequest request) {

        try {
            this.doAuthenticate(request.getEmail(), request.getPassword());
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new GenericResponse<>(0, "Invalid Username or Password", null));
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        Register emailRegister = authService.isEmailRegister(request.getEmail()).orElseThrow();

        String token = this.helper.generateToken(userDetails);
        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(emailRegister.getUsername())
                .email(emailRegister.getEmail())
                .mobileNo(emailRegister.getMobileNo())
                .build();

        return ResponseEntity.ok(new GenericResponse<>(1, "", response));
    }

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credentials Invalid !!");
    }
}