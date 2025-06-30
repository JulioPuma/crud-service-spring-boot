package com.template.springproject.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "server")
@Getter
@Setter
public class ApplicationProperties {
    private String port;
    private String name;
    private String domain;
}
