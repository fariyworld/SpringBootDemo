package com.mace.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * description: security config
 * <br />
 * Created by mace on 23:47 2018/6/23.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    进行加密 $2a$10$n8iAWNartbvVTbvErFKiZOy3k0J.Ad2hCK1KiQUk1ZgxFmw41y08C
//    BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();

    @Autowired
    protected void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("mace")
                .password("$2a$10$n8iAWNartbvVTbvErFKiZOy3k0J.Ad2hCK1KiQUk1ZgxFmw41y08C")
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html/**",
                        "/webjars/**",
                        "/api2doc/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .permitAll()
                .and()
                .formLogin()
                .permitAll()
                .and().httpBasic();
        // 问题：为了帮助保护在此网站中输入的信息的安全，此内容的发布者不允许在框架中显示该信息。
        // 原因：spring security安全框架设置了默认的X-Frame-Options为DENY,更改spring security配置
        // 解决代码
        http.headers().frameOptions().disable();
        http.csrf().disable();
    }
}
