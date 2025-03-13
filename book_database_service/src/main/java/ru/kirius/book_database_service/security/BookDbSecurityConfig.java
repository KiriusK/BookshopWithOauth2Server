package ru.kirius.book_database_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BookDbSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(config -> config
                        .requestMatchers("/books/**").permitAll()
                        .requestMatchers("/admin/**").access(OAuth2AuthorizationManagers.hasScope("resource.read"))
                        .anyRequest().denyAll()
                )
                .oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()))
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        ;
        return http.build();
    }
}
