package Authcom.example.Authentication.controller;

import Authcom.example.Authentication.dto.*;
import Authcom.example.Authentication.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/api")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    // ✅ Signup API
    @PostMapping("/signup")
    @PreAuthorize("permitAll()")
    public ResponseEntity<ApiResponseDto<SignupResponseDto>> signup(
            @RequestBody SignupRequestDto request) {

        SignupResponseDto response = authService.signup(request);

        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        true,
                        "User registered successfully",
                        response
                )
        );
    }

    // ✅ Login API
    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<ApiResponseDto<LoginResponseDto>> login(
            @RequestBody LoginRequestDto request) {

        LoginResponseDto response = authService.login(request);

        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        true,
                        "Login successful",
                        response
                )
        );
    }
}
