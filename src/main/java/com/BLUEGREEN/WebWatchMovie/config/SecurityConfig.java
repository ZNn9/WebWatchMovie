package com.BLUEGREEN.WebWatchMovie.config;

import com.BLUEGREEN.WebWatchMovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService; // Đảm bảo @Autowired ở đây

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/css/**", "/js/**", "/", "/oauth/**", "/register", "/error", "/api/**")
                        .permitAll() // Allow these URLs without authentication
                        .anyRequest().authenticated() // All other URLs require authentication
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll() // Allow login page to be accessed without authentication
                )

                .logout(logout -> logout
                        .permitAll() // Allow logout to be accessed without authentication
                )
                // ĐĂNG NHẬP BẰNG GOOGLE
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/login") // Trang đăng nhập cho OAuth2.
                        .defaultSuccessUrl("/movies") // Trang sau khi đăng nhập thành công.
                        .failureUrl("/login?error") // Trang đăng nhập thất bại.
                )
                .csrf().disable();
        // Disable CSRF for simplicity; enable in production

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("ADMIN").password("{noop}admin123").roles("ADMIN");

/*        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}