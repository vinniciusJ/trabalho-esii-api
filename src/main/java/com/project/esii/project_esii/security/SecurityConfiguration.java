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
            "/event-participant", // Criar participante
            "/main-event-type",   // Listar tipos de evento
            "/main-event",         // Listar eventos
            "/authentication/login",
            "/swagger-ui/*",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
    };

    public static final String[] ENDPOINTS_PARTICIPANT = {
            "/event-participant/{id}", // Obter participante por ID
            "/event-participant/{id}"  // Deletar participante
    };

    public static final String[] ENDPOINTS_MANAGER = {
            "/event-manager/{id}",       // Obter gerenciador por ID
            "/main-event",               // Criar evento
            "/main-event-action",        // Criar ação
            "/main-event/{id}",          // Deletar evento
            "/main-event-action/{id}"    // Deletar ação
    };

    public static final String[] ENDPOINTS_ADMIN = {
            "/**" // Admin tem acesso a tudo
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

                        .requestMatchers(HttpMethod.POST, ENDPOINTS_PUBLIC).permitAll()
                        .requestMatchers(HttpMethod.GET, ENDPOINTS_PUBLIC).permitAll()

                        .requestMatchers(HttpMethod.GET, ENDPOINTS_PARTICIPANT).hasRole("EVENT_PARTICIPANT")
                        .requestMatchers(HttpMethod.DELETE, ENDPOINTS_PARTICIPANT).hasRole("EVENT_PARTICIPANT")

                        .requestMatchers(HttpMethod.GET, ENDPOINTS_MANAGER).hasRole("EVENT_MANAGER")
                        .requestMatchers(HttpMethod.POST, ENDPOINTS_MANAGER).hasRole("EVENT_MANAGER")
                        .requestMatchers(HttpMethod.DELETE, ENDPOINTS_MANAGER).hasRole("EVENT_MANAGER")

                        .requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMIN")

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