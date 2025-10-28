package com.yohan.service.config;

import com.yohan.service.auth.CustomOAuth2FailureHandler;
import com.yohan.service.auth.CustomOAuth2Service;
import com.yohan.service.auth.CustomOAuth2SuccessHandler;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(
      CustomOAuth2Service service,
      CustomOAuth2SuccessHandler successHandler,
      CustomOAuth2FailureHandler failureHandler,
      HttpSecurity http)
      throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable)
        .cors(
            httpSecurityCorsConfigurer ->
                httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()))
        .authorizeHttpRequests(configurer -> configurer.anyRequest().permitAll())
        .oauth2ResourceServer(configurer -> configurer.jwt(Customizer.withDefaults()))
        .oauth2Login(
            oauth2Login ->
                oauth2Login
                    .userInfoEndpoint(userInfo -> userInfo.userService(service))
                    .successHandler(successHandler)
                    .failureHandler(failureHandler))
        .build();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    var configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("*"));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("*"));
    var source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public JwtDecoder jwtDecoder(JwtPropertiesService jwtPropertiesService) {
    return NimbusJwtDecoder.withSecretKey(jwtPropertiesService.getKey()).build();
  }
}
