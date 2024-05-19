package com.web.studentsystem.controller;

import com.web.studentsystem.dto.JwtAuthenticationResponse;
import com.web.studentsystem.dto.RefreshTokenRequest;
import com.web.studentsystem.dto.SignInRequest;
import com.web.studentsystem.dto.SignUpRequest;
import com.web.studentsystem.entities.User;
import com.web.studentsystem.services.AuthenticationService;
import com.web.studentsystem.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JWTService jwtService;
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }


    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signin(signInRequest));
    }

    @GetMapping("/token")
    public ResponseEntity<String> isTokenExpired(@RequestParam String token) {
        if(jwtService.isTokenExpired(token))  return ResponseEntity.ok("false");
        else return ResponseEntity.ok(("true"));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
    //them logout
//    @GetMapping("/logout")
//    public ResponseEntity<?> logoutUser() {
//        SecurityContextHolder.clearContext();
//        return ResponseEntity.ok("Logout successful");
//    }

}
