package com.BLUEGREEN.WebWatchMovie.config;

import com.BLUEGREEN.WebWatchMovie.service.CustomOAuth2UserService;
import com.BLUEGREEN.WebWatchMovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;


    public UserDetailsService userDetailsService() {
        return new UserService(); // Cung cấp dịch vụ xử lý chi tiết người dùng.
    }

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var auth = new DaoAuthenticationProvider(); // Tạo nhà cung cấp xác thực.
        auth.setUserDetailsService(userDetailsService()); // Thiết lập dịch vụ chi tiết người dùng.
        auth.setPasswordEncoder(passwordEncoder()); // Thiết lập cơ chế mã hóa mật khẩu.
        return auth; // Trả về nhà cung cấp xác thực.
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/static/**", "/css/**", "/img/**", "/js/**", "/sass/**", "/movies/**", "/fonts/**", "/icon/**",
                                "/admin-css/**", "/admin-js/**", "/admin-lib/**", "/admin-vendor/**",
                                "/oauth/**", "/user/register", "/user/login", "/error",
                                "/", "/api/**", "/movies/**", "/movie-details/**", "/**"
                        )
                        .permitAll()
                        .requestMatchers("/user/**")
                        .hasAnyAuthority(
                                "isPartner", "isView",
                                "isMaster", "isManager",
                                "isAdd", "isEdit", "isDelete"
                        )
                        .requestMatchers(
                                "/admin/**"
                        )
                        .hasAnyAuthority(
                                "isPartner",
                                "isMaster", "isManager",
                                "isAdd", "isEdit", "isDelete"
                        )
                        .anyRequest().authenticated()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/user/login")
                        .loginProcessingUrl("/user/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/user/login?error")
                        .permitAll()
                )
                .rememberMe(rememberMe -> rememberMe
                        .key("anime6")
                        .rememberMeCookieName("anime6")
                        .tokenValiditySeconds(24 * 60 * 60)
                        .userDetailsService(userDetailsService())
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/403")
                )
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/user/login")
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                                .userService(customOAuth2UserService)
                        )
                        .defaultSuccessUrl("/")
                        .failureUrl("/user/login?error")
                )
                .build();
    }
}