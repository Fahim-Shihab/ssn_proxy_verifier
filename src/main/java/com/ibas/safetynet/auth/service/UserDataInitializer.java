package com.ibas.safetynet.auth.service;
import com.ibas.safetynet.auth.model.ClientInfo;
import com.ibas.safetynet.auth.model.UserInfo;
import com.ibas.safetynet.auth.repository.ClientRepository;
import com.ibas.safetynet.auth.repository.UserRepository;
import com.ibas.safetynet.common.ReadProps;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReadProps readProps;

    @Override
    public void run(String... args) throws Exception {
        syncHspUser();
    }

    private void syncHspUser() {
        UserInfo userInfo = userRepository.findByUsername(readProps.getHspUsername()).orElse(new UserInfo());
        if (userInfo.getId() == null) {
            UserInfo user = new UserInfo();
            user.setUsername(readProps.getHspUsername());
            user.setPassword(passwordEncoder.encode(readProps.getHspPassword()));
            user.setEnabled(true);
            user.setAccountExpired(false);
            user.setCredentialsExpired(false);
            user.setAccountLocked(false);
            userRepository.save(user);
        } else {
            if (!passwordEncoder.matches(readProps.getHspPassword(), userInfo.getPassword())) {
                userInfo.setPassword(passwordEncoder.encode(readProps.getHspPassword()));
                userRepository.save(userInfo);
            }
        }

        ClientInfo clientInfo = clientRepository.findByUsername(readProps.getHspBasicUser()).orElse(new ClientInfo());
        if (clientInfo.getId() == null) {
            clientInfo.setUsername(readProps.getHspBasicUser());
            clientInfo.setPassword(passwordEncoder.encode(readProps.getHspBasicPwd()));
            clientInfo.setEnabled(true);
            clientInfo.setAccountExpired(false);
            clientInfo.setCredentialsExpired(false);
            clientInfo.setAccountLocked(false);
            clientRepository.save(clientInfo);
        } else {
            if (!passwordEncoder.matches(readProps.getHspBasicPwd(), clientInfo.getPassword())) {
                clientInfo.setPassword(passwordEncoder.encode(readProps.getHspBasicPwd()));
                clientRepository.save(clientInfo);
            }
        }
    }
}
