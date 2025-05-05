package com.ibas.safetynet.auth.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityDto implements GrantedAuthority {
    private String authority;
}
