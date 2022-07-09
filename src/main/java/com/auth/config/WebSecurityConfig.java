package com.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.httpBasic().disable();
        security.cors().configurationSource(request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.addAllowedMethod("*");
            config.addAllowedOrigin("*");
            config.addAllowedHeader("*");

            return config;
        }).and().csrf().disable();
    }
}
