package ru.kirius.admin_web_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
public class SecurityConfig {

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        StrictHttpFirewall firewall = new StrictHttpFirewall();
//        firewall.setAllowBackSlash(true);
//        firewall.setAllowSemicolon(true);
//        return (web) -> web.httpFirewall(firewall);
//    }
}
