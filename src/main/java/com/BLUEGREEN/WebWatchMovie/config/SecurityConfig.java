package com.BLUEGREEN.WebWatchMovie.config;

import com.BLUEGREEN.WebWatchMovie.service.CustomOAuth2UserService;
import com.BLUEGREEN.WebWatchMovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("ADMIN").password("{noop}admin123").roles("ADMIN");

        /*auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ....
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
//                .csrf().disable()
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/css/**", "/js/**", "/", "/oauth/**", "/register", "/login",
                                "/error", "/api/**", "/test/**")
                        .permitAll()
//                        .requestMatchers("/products/edit/**", "/products/add", "/products/delete", "categories/**")
//                        .hasAnyAuthority("ADMIN") // Chỉ cho phép ADMIN truy cập.
//                        .permitAll()
                        .anyRequest().authenticated()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // (Chưa chốt địa chỉ)
                        .logoutSuccessUrl("/login") // Trang chuyển hướng sau khi đăng xuất. (Chưa chốt địa chỉ)
                        .deleteCookies("JSESSIONID") // Xóa cookie.
                        .invalidateHttpSession(true) // Hủy phiên làm việc.
                        .clearAuthentication(true) // Xóa xác thực.
                        .permitAll()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login") // Trang đăng nhập. (Chưa chốt địa chỉ)
                        .loginProcessingUrl("/login") // URL xử lý đăng nhập. // (Chưa chốt địa chỉ)
                        .defaultSuccessUrl("/products", true) // Trang sau đăng nhập thành công.
                        .failureUrl("/login?error") // Trang đăng nhập thất bại.
                        .permitAll()
                )
                .rememberMe(rememberMe -> rememberMe
                        .key("anime6")
                        .rememberMeCookieName("anime6")
                        .tokenValiditySeconds(24 * 60 * 60) // Thời gian nhớ đăng nhập.
                        .userDetailsService(userDetailsService())
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/403") // Trang báo lỗi khi truy cập không được phép.
                )
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/login") // (Chưa chốt địa chỉ)
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                                .userService(customOAuth2UserService)
                        )
                        .defaultSuccessUrl("/movies")
                        .failureUrl("/login?error")
                )
                .httpBasic(httpBasic -> httpBasic
                        .realmName("anime6") // Tên miền cho xác thực cơ bản.
                )
                .build();


    }


}
