package dev.gagnon.Benue_Produce_Logistics_Api.security.config;

import dev.gagnon.Benue_Produce_Logistics_Api.security.filter.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
public class SecurityConfig {
    private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**",
    "/api/v1/farmer/register",
    "/api/v1/farmer/findBy/{email}",
    "/api/v1/product/findAll",
    "/api/v1/product/findBy/{id}",
    "/api/v1/product/delete/{id}",
    "/api/v1/logistics/register",
    "/api/v1/buyer/register",
    };
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers("/api/v1/product/add").hasAuthority("FARMER")
                            .requestMatchers("/api/v1/logistics/updateLocation").hasAuthority("LOGISTIC_USER")
                                .requestMatchers("/api/v1/logistics/sendDeliveryConfirmation").hasAuthority("LOGISTIC_USER")
                                .requestMatchers("/api/v1/order/**").hasAuthority("BUYER")
                                .requestMatchers("/api/v1/logistics/findAvailableRiders").hasAuthority("BUYER")
                                .requestMatchers("/api/v1/order/removeProductFromOrder").hasAuthority("BUYER")
                                .requestMatchers("/api/v1/logistics/confirmDelivery").hasAuthority("BUYER")
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider);
        return http.build();
    }


    @Bean
    public CorsFilter corsFilter() {
        CorsConfigurationSource source = corsConfigurationSource();
        return new CorsFilter(source);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
