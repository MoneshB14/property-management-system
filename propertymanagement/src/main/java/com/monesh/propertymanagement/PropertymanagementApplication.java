package com.monesh.propertymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.monesh.propertymanagement.properties.EncryptionProperties;

@SpringBootApplication
@EnableConfigurationProperties(EncryptionProperties.class)
public class PropertymanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertymanagementApplication.class, args);
	}

}
