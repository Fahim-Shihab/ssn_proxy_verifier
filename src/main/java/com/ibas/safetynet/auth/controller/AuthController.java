package com.ibas.safetynet.auth.controller;

import com.ibas.safetynet.auth.service.AuthService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/token", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<?> authenticate(
            @RequestHeader @NotBlank String authorization,
            @RequestParam @NotBlank String username,
            @RequestParam @NotBlank String password,
            @RequestParam @NotBlank String grant_type) {
        return authService.authenticate(authorization, username, password, grant_type);
    }
}
