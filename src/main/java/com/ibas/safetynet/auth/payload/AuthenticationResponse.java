package com.ibas.safetynet.auth.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationResponse implements Serializable {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expires_in;
    private String client_id;
    private String userName;
    private Instant issued;
    private Instant expires;
}
