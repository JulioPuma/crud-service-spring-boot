package com.template.springproject.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class ApplicationProperties {
  
  private List<String> availableDocuments = new ArrayList<>();
  
}
