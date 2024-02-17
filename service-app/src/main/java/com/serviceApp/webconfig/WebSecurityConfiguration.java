package com.serviceApp.webconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration {


    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests((authorize) -> {
            authorize.requestMatchers(HttpMethod.POST, "/createCustomer").permitAll();
            authorize.requestMatchers(HttpMethod.POST, "/api/signUp").permitAll();
            authorize.requestMatchers(HttpMethod.POST, "/api/login").permitAll();
            authorize.requestMatchers(HttpMethod.POST, "/authenticate").permitAll();
            authorize.requestMatchers(HttpMethod.POST, "/postQuestion").permitAll();
            authorize.requestMatchers(HttpMethod.POST, "/uploadData").permitAll();
            authorize.requestMatchers(HttpMethod.PUT, "/api/**");
            authorize.anyRequest().authenticated();

        }).httpBasic(Customizer.withDefaults());
        return httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class).build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
