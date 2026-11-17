package com.example.demo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
public class SecurityConfig {
 @Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {
  return http.csrf(csrf -> csrf.disable())
   .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
   .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/token").permitAll().anyRequest().authenticated())
   .exceptionHandling(ex -> ex.authenticationEntryPoint((request, response, exception) ->
    response.sendError(401, "Unauthorized: provide a valid Bearer token")))
   .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
 }
}
