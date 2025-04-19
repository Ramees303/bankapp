package com.ramees.internet_banking_api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
        return http.csrf(csrf -> csrf.disable())
                .authorizeExchange(exchange-> exchange.pathMatchers("/eureka/**").permitAll()
//                        .pathMatchers(HttpMethod.POST,"/user/api/v1/bank-user/register").permitAll()
//                        .pathMatchers(HttpMethod.PATCH,"/user/api/v1/bank-user/updateStatus/*").permitAll()
                        .pathMatchers("/internet-banking/**").permitAll()
                        .anyExchange().authenticated())
                .oauth2ResourceServer(oauth-> oauth.jwt(Customizer.withDefaults()))
                .build();

    }
}
