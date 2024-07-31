package com.reader.scanner.controller;

import com.reader.scanner.model.User;
import com.reader.scanner.service.AuthenticationService;
import com.reader.scanner.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        try {
            User user = authenticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            String token = jwtUtil.generateToken(user.getEmail());
            String bearerToken = "Bearer " + token;
            Map<String, String> response = new HashMap<>();
            response.put("token", bearerToken);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}

// added 28th line
//changes at 30th line with bearertoken
