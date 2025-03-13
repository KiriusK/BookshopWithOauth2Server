package ru.kirius.gateway_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;

@Configuration
@EnableWebSecurity
public class GatewayConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(config -> config
                        .requestMatchers("/order/**").permitAll()
                        .requestMatchers("/pdf").permitAll()
                        .requestMatchers("/fb2").permitAll()
                        .requestMatchers("/cover").permitAll()
                        .requestMatchers("/books/**").permitAll()
                        .requestMatchers("/admin/**").permitAll()
                        .requestMatchers("/cover/**").permitAll()
                        .requestMatchers("/fb2/**").permitAll()
                        .requestMatchers("/pdf/**").permitAll()
                        .requestMatchers("/order/admin/**").permitAll()
                        .anyRequest().denyAll()
                )
        ;
        return http.build();
    }
}

