package com.ikjunweb.config;

import com.ikjunweb.service.pricipal.PrincipalOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final PrincipalOAuth2UserService principalOAuth2UserService;

    @Autowired
    public SecurityConfig(PrincipalOAuth2UserService principalOAuth2UserService) {
        this.principalOAuth2UserService = principalOAuth2UserService;
    }

    @Bean
    public BCryptPasswordEncoder encoderPassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
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
                        .loginProcessingUrl("/ikjun/login")   // /login??? ???????????? ??????????????? ???????????? ?????? ????????? ????????????
                        .defaultSuccessUrl("/ikjun"))
                .logout().logoutSuccessUrl("/ikjun")
                .and()
                .oauth2Login(form -> form
                        .loginPage("/ikjun/loginForm")    //?????? ???????????? ???????????? ?????????????????? ????????? ?????????????????? ??????
                        .userInfoEndpoint()
                        .userService(principalOAuth2UserService))
                .logout().logoutSuccessUrl("/ikjun")
                .and()
                .build();
    }

}
