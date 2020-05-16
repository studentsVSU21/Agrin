package ru.vsu.cs.config;

import com.nimbusds.jose.JWSAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWSHeaderConfig {

    @Bean
    public com.nimbusds.jose.JWSHeader getJWSHeader () {
        return  new com.nimbusds.jose.JWSHeader(JWSAlgorithm.HS256);
    }
}
