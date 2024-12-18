package com.project.esii.project_esii.security;

import com.project.esii.project_esii.authentication.utils.UserAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserAuthenticationFilter userAuthenticationFilter;

    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            // authentication
            "/authentication/login",

            "/swagger-ui/*",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",


            // Event Participant
            "/event-participant/{id}",
            "/event-participant",
            "/event-participant/verify-email/{id}",

            // Event Manager
            "/event-manager",
            "/event-manager/verify-email/{id}",
            "/event-manager/{id}"
    };

    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {

    };

    public static final String[] ENDPOINTS_EVENT_PARTICIPANT = {

    };

    public static final String[] ENDPOINTS_EVENT_MANAGER = {
            // Main Event
            "/main-event/"
    };


    public static final String[] ENDPOINTS_ADMIN = {

    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(cors -> cors
                        .configurationSource(request -> {
                            var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
                            corsConfiguration.setAllowedOriginPatterns(List.of("*"));
                            corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                            corsConfiguration.setAllowedHeaders(List.of("*"));
                            corsConfiguration.setAllowCredentials(true);
                            return corsConfiguration;
                        })
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()

                        .requestMatchers(HttpMethod.GET, "/main-event", "/main-event/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/main-event-action", "/main-event-action/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/main-event-type", "/main-event-type/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/main-event", "/main-event/**").hasRole("EVENT_MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/main-event", "/main-event/**").hasRole("EVENT_MANAGER")
                        .requestMatchers(HttpMethod.POST, "/main-event-action", "/main-event-action/**").hasRole("EVENT_MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/main-event-action", "/main-event-action/**").hasRole("EVENT_MANAGER")
                        .requestMatchers(HttpMethod.POST, "/main-event-type", "/main-event-type/**").hasRole("EVENT_MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/main-event-type", "/main-event-type/**").hasRole("EVENT_MANAGER")

//                        .requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMIN")
//                        .requestMatchers(ENDPOINTS_EVENT_PARTICIPANT).hasRole("EVENT_PARTICIPANT")
//                        .requestMatchers(ENDPOINTS_EVENT_MANAGER).hasRole("EVENT_MANAGER")
                        .anyRequest().denyAll()
                )
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}