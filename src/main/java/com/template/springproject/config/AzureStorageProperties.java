package com.template.springproject.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "azure.storage")
@Configuration
@Getter
@Setter
public class AzureStorageProperties {
  private String connectionString;
  private String containerName;
}
