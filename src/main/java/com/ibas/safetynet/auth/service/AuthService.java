package com.ibas.safetynet.auth.service;

import com.ibas.safetynet.auth.dao.AuthDao;
import com.ibas.safetynet.common.ErrorResponse;
import com.ibas.safetynet.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthDao authDao;
    private final JwtService jwtService;

    public ResponseEntity<?> authenticate(String authorizationHeader, String username, String password, String grant_type) {
        if (!"password".equals(grant_type)) {
            List<String> details = new ArrayList<>();
            details.add("Invalid grant type");
            ErrorResponse error = new ErrorResponse("Validation Failed", details);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        String[] credentials = jwtService.decodeBasicAuth(authorizationHeader);
        String clientId = credentials[0];

        return authDao.authenticate(clientId, username, password);
    }
}
