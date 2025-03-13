package ru.kirius.data_storage_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class DataStorageSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(config -> config
                        .requestMatchers("/cover").permitAll()
                        .requestMatchers("/fb2").permitAll()
                        .requestMatchers("/pdf").permitAll()
                        .requestMatchers("/cover/**").access(OAuth2AuthorizationManagers.hasScope("resource.read"))
                        .requestMatchers("/fb2/**").access(OAuth2AuthorizationManagers.hasScope("resource.read"))
                        .requestMatchers("/pdf/**").access(OAuth2AuthorizationManagers.hasScope("resource.read"))
                        .anyRequest().denyAll()
                )
                .oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()))
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        ;
        return http.build();
    }
}
