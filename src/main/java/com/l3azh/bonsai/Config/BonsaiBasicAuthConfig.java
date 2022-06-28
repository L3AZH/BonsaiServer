package com.l3azh.bonsai.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@Order(1)
@EnableWebSecurity
@RequiredArgsConstructor
public class BonsaiBasicAuthConfig {

    private final BonsaiBasicAuthFilter basicAuthFilter;
    private final BonsaiBasicAuthEntryPoint basicAuthEntryPoint;

    @Bean
    public SecurityFilterChain basicAuthConfig(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(basicAuthEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .antMatcher("/api/auth/**").authorizeRequests()
                .anyRequest().authenticated().and().httpBasic();
        httpSecurity.addFilterBefore(basicAuthFilter, BasicAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
