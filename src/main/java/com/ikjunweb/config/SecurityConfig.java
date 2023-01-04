package com.ikjunweb.config;

import com.ikjunweb.service.pricipal.PrincipalOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/ikjun/board/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin(form -> form
                        .loginPage("/ikjun/loginForm")
                        .loginProcessingUrl("/ikjun/login")   // /login이 호출되면 시큐리티가 낚아채어 대신 로그인 진행해줌
                        .defaultSuccessUrl("/ikjun"))
                .logout().logoutSuccessUrl("/ikjun")
                .and()
                .oauth2Login(form -> form
                        .loginPage("/ikjun/loginForm")    //구글 로그인이 완료되면 엑세스토큰과 사용자 프로필정보를 받음
                        .userInfoEndpoint()
                        .userService(principalOAuth2UserService))
                .logout().logoutSuccessUrl("/ikjun")
                .and()
                .build();
    }

}
