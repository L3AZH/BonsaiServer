package com.l3azh.bonsai.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BonsaiAppConfig {

    @Bean
    public ModelMapper provideModelMapper(){
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder providePasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
