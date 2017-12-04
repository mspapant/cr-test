package com.synnous.cr.api.config;

import com.synnous.cr.core.property.EmailProperties;
import com.synnous.cr.core.property.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@EnableScheduling
@SpringBootApplication
@ComponentScan("com.synnous")
@EnableAutoConfiguration(exclude = RepositoryRestMvcAutoConfiguration.class)
@EnableConfigurationProperties({StorageProperties.class, EmailProperties.class})
public class CareRoomsApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CareRoomsApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
