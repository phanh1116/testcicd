package com.web.studentsystem.services;

import com.web.studentsystem.dto.JwtAuthenticationResponse;
import com.web.studentsystem.dto.RefreshTokenRequest;
import com.web.studentsystem.dto.SignInRequest;
import com.web.studentsystem.dto.SignUpRequest;
import com.web.studentsystem.entities.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SignInRequest signInRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
