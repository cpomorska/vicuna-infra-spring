package com.scprojekt.infrastructure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "datasource")
public class JpaProperties {
    private String driverClassName;
    private String dataBaseUrl;
    private String userName;
    private String password;
}
