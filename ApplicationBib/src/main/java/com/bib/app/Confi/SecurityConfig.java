package com.bib.app.Confi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import com.bib.app.service.MyUserDetailService;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired 
    private MyUserDetailService userDetailService;

    @Bean 
    public UserDetailsService userDetailsService() { 
        return userDetailService; 
    }

    @Bean 
    public AuthenticationProvider authenticationProvider() { 
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); 
        provider.setUserDetailsService(userDetailService); 
        provider.setPasswordEncoder(passwordEncoder()); 
        return provider; 
    }

    @Bean 
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder(); 
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disable CSRF for SPA (Single Page Application)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/register", "/api/events/**", "/api/admins/**").permitAll() // Public endpoints
                .requestMatchers("/admin/**").hasRole("ADMIN") // Only allow admin access to certain routes
                .anyRequest().authenticated() // Any other request requires authentication
            )
            .formLogin(form -> form.permitAll()) // Allow form login for Spring Security
            .logout(logout -> logout.permitAll()) // Allow logout
            .cors(cors -> cors.configurationSource(corsConfigurationSource())); // Apply CORS configuration from a custom bean

        return http.build();
    }

    // CORS Configuration Bean
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // Allow Angular frontend to access
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Allowed HTTP methods
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Allowed headers
        configuration.setAllowCredentials(true); // Allow credentials (cookies, authorization headers)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply the CORS configuration globally

        return source;
    }
}
