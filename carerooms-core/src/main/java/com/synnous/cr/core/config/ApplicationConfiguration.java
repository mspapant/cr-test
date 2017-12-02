package com.synnous.cr.core.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The application configuration
 *
 * @author : Manos Papantonakos.
 */
@Configuration
@EnableCaching
@EnableScheduling
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = RepositoryRestMvcAutoConfiguration.class)
public class ApplicationConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder("R')dGj#MW8!;v`F^^6s&?nP+<*6;P7dC%x^]d24B4Uq&jz8<)dp)wT");
    }

    public static void main(String[] s) {
        System.out.println(new StandardPasswordEncoder("R')dGj#MW8!;v`F^^6s&?nP+<*6;P7dC%x^]d24B4Uq&jz8<)dp)wT").encode("12345678"));
    }
}
