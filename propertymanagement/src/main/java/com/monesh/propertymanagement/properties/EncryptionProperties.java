package com.monesh.propertymanagement.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "encryption")
public class EncryptionProperties {
    private String algorithm;
    private String transformation;
    private String secretKey;
}