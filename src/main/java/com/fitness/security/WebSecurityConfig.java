package com.fitness.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final Set<String> allowedHttpMethods;
    private final UserDetailsService userDetails;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetails, BCryptPasswordEncoder encoder) {
        this.userDetails = userDetails;
        this.encoder = encoder;

        // Initialize allowed HTTP methods here
        String[] httpMethods = {"DELETE", "GET", "HEAD", "OPTIONS", "PATCH", "POST", "PUT", "CONNECT"};
        this.allowedHttpMethods = Arrays.stream(httpMethods).collect(Collectors.toSet());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/", "/home", "/signup", "/logout", "/adduser", "/api/*").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/fonts/**", "/assets/**").permitAll()
                        .anyRequest().hasRole("USER")
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .failureUrl("/login?login_error=1")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                );
        return http.build();
    }

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(encoder);
    }


    @Bean
    public HttpFirewall httpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowedHttpMethods(allowedHttpMethods);
        return firewall;
    }
}
