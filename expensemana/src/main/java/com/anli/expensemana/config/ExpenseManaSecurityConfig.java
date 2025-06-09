package com.anli.expensemana.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//TODO: Concfigure it for the production-level later
public class ExpenseManaSecurityConfig {

    /**
     *  In case I need more configurations, I leave this code snippet here.
     */
/*     public SecurityFilterChain SecurityChainFilter (HttpSecurity http) throws Exception {
       http
                .csrf(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());
                //.addFilterAfter(new OwnFilter(), AnonymousAuthenticationFilter.class);

        return http.build();
    } */


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/signup").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/auth/logout").authenticated()
                        .requestMatchers("/user/**").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin().disable()
                .httpBasic().disable();

        return http.build();

    }
}
