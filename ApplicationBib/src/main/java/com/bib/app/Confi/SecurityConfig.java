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

import com.bib.app.service.MyUserDetailService;

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
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            	/*.requestMatchers("/public/fct","/**").permitAll()
                .requestMatchers("/protected/fct").authenticated()*/
            		.requestMatchers("/").permitAll()
            	//	.requestMatchers("/register/user").permitAll()
            		.requestMatchers("/participants/add","/events/getall","/login/**").permitAll()
            		.requestMatchers("/").hasRole("ADMIN")
            //		.requestMatchers("/emprunts/**", "/livres/**", "/membres/**").hasRole("USER")
            //		.requestMatchers("/api/**").hasRole("USER")
            //		.requestMatchers("/admin/**").hasRole("ADMIN")
            	//	.anyRequest().authenticated()
            )
           // .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .formLogin(t -> t.permitAll());

        return http.build();
    }
    
    @Bean
    public PasswordEncoder password() {
    	return new BCryptPasswordEncoder();
    }
}
