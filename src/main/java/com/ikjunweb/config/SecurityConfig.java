package com.ikjunweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encoderPassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/ikjun/board/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin(form -> form
                        .loginPage("/ikjun/loginForm")
                        .loginProcessingUrl("/login")   // /login이 호출되면 시큐리티가 낚아채어 대신 로그인 진행해줌
                        .defaultSuccessUrl("/"))
                .logout().logoutSuccessUrl("/")
                .and()
                .build();
    }

}
