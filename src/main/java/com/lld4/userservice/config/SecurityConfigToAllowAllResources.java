package com.lld4.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigToAllowAllResources {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) ->
                {
                    try {
                        requests.anyRequest().permitAll()
                                .and().cors().disable()// Cross-Origin Resource Sharing (CORS)
                                .csrf().disable(); // Cross Site Request Forgery (CSRF)
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                );


        return http.build();
    }
}
