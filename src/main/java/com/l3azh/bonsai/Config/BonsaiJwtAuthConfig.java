package com.l3azh.bonsai.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Order(2)
@RequiredArgsConstructor
public class BonsaiJwtAuthConfig {
    private final BonsaiJwtAuthFilter jwtAuthFilter;

    private final BonsaiJwtAuthEntryPoint jwtErrorHandler;

    private final BonsaiJwtAuthManager jwtAuthManager;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return jwtAuthManager;
    }

    @Bean
    public SecurityFilterChain jwtAuthConfig(HttpSecurity config) throws Exception {
        config.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtErrorHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated();
        config.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return config.build();
    }
}
