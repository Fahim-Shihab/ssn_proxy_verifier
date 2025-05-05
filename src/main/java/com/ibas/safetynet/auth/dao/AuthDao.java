package com.ibas.safetynet.auth.dao;

import com.ibas.safetynet.auth.payload.AuthenticationResponse;
import com.ibas.safetynet.auth.repository.UserRepository;
import com.ibas.safetynet.config.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.Instant;
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
                    Instant issued = Instant.now();
                    Instant expires = issued.plusSeconds(expires_in);

                    AuthenticationResponse authenticationResponse = new AuthenticationResponse();
                    authenticationResponse.setAccess_token(access_token);
                    authenticationResponse.setRefresh_token(refresh_token);
                    authenticationResponse.setToken_type("Bearer");
                    authenticationResponse.setClient_id(clientId);
                    authenticationResponse.setUserName(username);
                    authenticationResponse.setExpires_in(expires_in);
                    authenticationResponse.setIssued(issued);
                    authenticationResponse.setExpires(expires);

                    return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
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
