package com.ibas.safetynet.auth.payload;

import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Getter
public class JwtUserDto implements UserDetails {

    final UserDto userDto;
    final List<AuthorityDto> authorities;

    public JwtUserDto(UserDto userDto, List<AuthorityDto> authorities) {
        this.userDto = userDto;
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return userDto.getPassword();
    }

    @Override
    public String getUsername() {
        return userDto.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return userDto.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userDto.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userDto.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return userDto.isEnabled();
    }
}
