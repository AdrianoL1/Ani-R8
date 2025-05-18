package com.adrianoL.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtDecoder jwtDecoder;
    private final JwtAuthenticationConverter jwtAuthenticationConverter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        var authProvider = new DaoAuthenticationProvider(passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(authProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
                .oauth2ResourceServer(
                        resourceServer -> {
                            resourceServer.jwt(jwt -> {
                                jwt.decoder(jwtDecoder);
                                jwt.jwtAuthenticationConverter(jwtAuthenticationConverter);
                            });
                            resourceServer.authenticationEntryPoint(customAuthenticationEntryPoint);
                            resourceServer.accessDeniedHandler(customAccessDeniedHandler);
                        }
                )
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/v1/auth/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST,"/api/v1/anime").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE,"/api/v1/anime/**").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.PUT,"/api/v1/anime/**").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.POST,"/api/v1/manga").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE,"/api/v1/manga/**").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.PUT,"/api/v1/manga/**").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.POST,"/api/v1/genre").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE,"/api/v1/genre/**").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.PUT,"/api/v1/genre/**").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.GET, "/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
