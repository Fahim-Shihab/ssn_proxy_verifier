package com.ibas.safetynet.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ibas")
@Getter
@Setter
public class ReadProps {
    private String hspUsername;
    private String hspPassword;
    private String hspBasicUser;
    private String hspBasicPwd;
}
