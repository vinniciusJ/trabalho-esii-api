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

    public static final String[] ENDPOINTS_PUBLIC = {
            "/swagger-ui/*",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
    };

    // Authentication Controller
    public static final String[] ENDPOINTS_AUTHENTICATION = {
            "/authentication/login"
    };

    // Event Participant Controller
    public static final String[] ENDPOINTS_EVENT_PARTICIPANT_PUBLIC = {
            "/event-participant"
    };

    public static final String[] ENDPOINTS_EVENT_PARTICIPANT_PROTECTED = {
            "/event-participant/{id}"
    };

    // Event Manager Controller
    public static final String[] ENDPOINTS_EVENT_MANAGER_PROTECTED = {
            "/event-manager/{id}"
    };

    // Main Event Controller
    public static final String[] ENDPOINTS_MAIN_EVENT_PUBLIC = {
            "/main-event"
    };

    public static final String[] ENDPOINTS_MAIN_EVENT_PROTECTED = {
            "/main-event",       // POST
            "/main-event/{id}"   // DELETE
    };

    // Main Event Action Controller
    public static final String[] ENDPOINTS_MAIN_EVENT_ACTION_PROTECTED = {
            "/main-event-action",      // POST
            "/main-event-action/{id}"  // DELETE
    };

    // Main Event Type Controller
    public static final String[] ENDPOINTS_MAIN_EVENT_TYPE_PUBLIC = {
            "/main-event-type"
    };

    // Admin - Tem acesso a tudo
    public static final String[] ENDPOINTS_ADMIN = {
            "/**"
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

                        // Public
                        .requestMatchers(HttpMethod.POST, ENDPOINTS_PUBLIC).permitAll()

                        // Authentication
                        .requestMatchers(HttpMethod.POST, ENDPOINTS_AUTHENTICATION).permitAll()

                        // Event Participant
                        .requestMatchers(HttpMethod.POST, ENDPOINTS_EVENT_PARTICIPANT_PUBLIC).permitAll()
                        .requestMatchers(HttpMethod.GET, ENDPOINTS_EVENT_PARTICIPANT_PROTECTED).hasRole("EVENT_PARTICIPANT")
                        .requestMatchers(HttpMethod.DELETE, ENDPOINTS_EVENT_PARTICIPANT_PROTECTED).hasRole("EVENT_PARTICIPANT")

                        // Event Manager
                        .requestMatchers(HttpMethod.GET, ENDPOINTS_EVENT_MANAGER_PROTECTED).hasRole("EVENT_MANAGER")

                        // Main Event
                        .requestMatchers(HttpMethod.GET, ENDPOINTS_MAIN_EVENT_PUBLIC).permitAll()
                        .requestMatchers(HttpMethod.POST, ENDPOINTS_MAIN_EVENT_PROTECTED).hasRole("EVENT_MANAGER")
                        .requestMatchers(HttpMethod.DELETE, ENDPOINTS_MAIN_EVENT_PROTECTED).hasRole("EVENT_MANAGER")

                        // Main Event Action
                        .requestMatchers(HttpMethod.POST, ENDPOINTS_MAIN_EVENT_ACTION_PROTECTED).hasRole("EVENT_MANAGER")
                        .requestMatchers(HttpMethod.DELETE, ENDPOINTS_MAIN_EVENT_ACTION_PROTECTED).hasRole("EVENT_MANAGER")

                        // Main Event Type
                        .requestMatchers(HttpMethod.GET, ENDPOINTS_MAIN_EVENT_TYPE_PUBLIC).permitAll()

                        // Admin
                        .requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMIN")

                        // Qualquer outro endpoint é negado
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
