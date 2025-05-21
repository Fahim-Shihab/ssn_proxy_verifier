package com.ibas.safetynet.common;


import com.ibas.safetynet.auth.payload.JwtUserDto;
import com.ibas.safetynet.auth.payload.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utility {

    public static Integer getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUserDto userPrincipal = (JwtUserDto) authentication.getPrincipal();
        UserDto userDto = userPrincipal.getUserDto();

        return userDto.getId();
    }
}
