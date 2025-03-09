package com.ibas.safetynet.auth.dao;

import com.ibas.safetynet.auth.repository.UserRepository;
import com.ibas.safetynet.config.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AuthDao {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    public ResponseEntity<?> authenticate(String clientId, String username, String password) {
        try {
            var userInfo = userRepository.findByUsername(username).orElse(null);
            if (userInfo != null) {
                if (passwordEncoder.matches(password, userInfo.getPassword())) {
                    var access_token = jwtService.generateToken(userInfo);
                    var refresh_token = jwtService.generateRefreshToken(userInfo);
                    var expires_in = jwtExpiration / 1000;
                    var issued = new Date();
                    var expires = String.valueOf(issued.toInstant().plusSeconds(expires_in));

                    Map<String, Object> response = new HashMap<>();
                    response.put("access_token", access_token);
                    response.put("refresh_token", refresh_token);
                    response.put("token_type", "Bearer");
                    response.put("client_id", clientId);
                    response.put("userName", username);
                    response.put("expires_in", expires_in);
                    response.put("issued", issued);
                    response.put("expires", expires);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username");
            }
        } catch (Exception e) {
            log.error("Error while authenticating user", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error while authenticating user");
        }
    }
}
