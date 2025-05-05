package com.ibas.safetynet.config;

import com.ibas.safetynet.auth.model.UserInfo;
import com.ibas.safetynet.auth.payload.JwtUserDto;
import com.ibas.safetynet.auth.payload.UserDto;
import com.ibas.safetynet.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public JwtUserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<UserInfo> userInfoOptional = userRepository.findByUsername(username);
            if (userInfoOptional.isPresent()) {
                UserInfo user = userInfoOptional.get();
                    UserDto userDto = new UserDto();
                    BeanUtils.copyProperties(user, userDto);
                return new JwtUserDto(
                            userDto,
                            new ArrayList<>()
                    );
            } else {
                throw new UsernameNotFoundException("");
            }
        } catch (Exception ex) {
            return null;
        }
    }
}
