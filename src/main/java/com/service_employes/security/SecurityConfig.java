package com.service_employes.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${jwt.secret}")
    private String secret;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(cors -> cors
                        .configurationSource(request -> {
                            var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
                            corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
                            corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                            corsConfiguration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
                            return corsConfiguration;
                        })
                )
                .sessionManagement(sm-> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf-> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/contracts/file/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oa->oa.jwt(Customizer.withDefaults()))
                .build();
    }

    @Bean
    JwtDecoder jwtDecoder(){
        SecretKeySpec secretKeySpec=new SecretKeySpec(secret.getBytes(),"RSA");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
    }
}
