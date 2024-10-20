package com.springstudy.shawarma_cloud.config;

import com.springstudy.shawarma_cloud.model.User;
import com.springstudy.shawarma_cloud.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user != null) return user;

            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/tacos", "/api/orders/**").permitAll()
//                        .requestMatchers("/design", "/orders").hasRole("USER")
//                        .requestMatchers(HttpMethod.POST, "/api/ingredients").hasAuthority("SCOPE_writeIngredients")
//                        .requestMatchers(HttpMethod.DELETE, "/api/ingredients").hasAuthority("SCOPE_deleteIngredients")
//                        .requestMatchers(HttpMethod.GET, "api/orders").hasAuthority("SCOPE_readOrders")
//                        .requestMatchers(HttpMethod.POST, "api/orders").permitAll()
//                        .requestMatchers(HttpMethod.PATCH, "api/orders/**").hasAuthority("SCOPE_patchOrders")
//                        .requestMatchers(HttpMethod.DELETE, "api/orders/**").hasAuthority("SCOPE_deleteOrders")
//                        .requestMatchers(HttpMethod.GET, "api/shawarmas").hasAuthority("SCOPE_readShawarmas")
//                        .requestMatchers(HttpMethod.POST, "api/shawarmas").hasAuthority("SCOPE_writeShawarmas")
//                        .requestMatchers(HttpMethod.GET, "api/shawarmas/**").hasAuthority("SCOPE_readЗarticularShawarma")
                        .anyRequest().permitAll()
                )
                //.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .formLogin(loginForm -> loginForm
                        .loginPage("/login"))
                .logout(logout -> logout
                        .logoutSuccessUrl("/"))
                .csrf(csrfConfigurer -> csrfConfigurer
                        .ignoringRequestMatchers("/h2-console/**", "/api/**"))
                .headers(headersConfigurer -> headersConfigurer
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .build();
    }
}
