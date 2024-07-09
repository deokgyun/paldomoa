package com.paldomoa.common.config.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.paldomoa.auth.filter.JsonUsernamePasswordAuthenticationFilter;
import com.paldomoa.auth.filter.LoginFailureHandler;
import com.paldomoa.auth.filter.LoginSuccessJWTProvideHandler;
import com.paldomoa.auth.jwt.filter.JWTFilter;
import com.paldomoa.auth.jwt.service.JwtService;
import com.paldomoa.auth.service.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final ObjectMapper objectMapper;
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager()
        throws Exception {
        DaoAuthenticationProvider provider = daoAuthenticationProvider();
        provider.setPasswordEncoder(
            passwordEncoder());
        return new ProviderManager(provider);
    }

    @Bean
    public LoginSuccessJWTProvideHandler loginSuccessJWTProvideHandler() {
        return new LoginSuccessJWTProvideHandler(jwtService);
    }

    @Bean
    public LoginFailureHandler loginFailureHandler() {
        return new LoginFailureHandler();
    }


    @Bean
    public JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordLoginFilter()
        throws Exception {
        JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordLoginFilter = new JsonUsernamePasswordAuthenticationFilter(
            objectMapper);
        jsonUsernamePasswordLoginFilter.setAuthenticationManager(authenticationManager());
        jsonUsernamePasswordLoginFilter.setAuthenticationSuccessHandler(
            loginSuccessJWTProvideHandler());
        jsonUsernamePasswordLoginFilter.setAuthenticationFailureHandler(loginFailureHandler());
        return jsonUsernamePasswordLoginFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(auth -> auth.disable())

            .formLogin(auth -> auth.disable())

            .addFilterAfter(jsonUsernamePasswordLoginFilter(), LogoutFilter.class)
            .addFilterBefore(new JWTFilter(jwtService),
                JsonUsernamePasswordAuthenticationFilter.class)

            .httpBasic(auth -> auth.disable())

            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/login", "/logout", "/").permitAll()
                    .requestMatchers("/**").permitAll()
//                .requestMatchers("/api/admin/**").hasRole("ADMIN")
            )

            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            .cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
                                                       @Override
                                                       public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                                                           CorsConfiguration config = new CorsConfiguration();
                                                           config.setAllowedOrigins(
                                                               Collections.singletonList("http://localhost:5173"));
                                                           config.setAllowedMethods(Collections.singletonList("*"));
                                                           config.setAllowCredentials(true);
                                                           config.setAllowedHeaders(Collections.singletonList("*"));
                                                           config.setMaxAge(3600L); //1시간
                                                           config.setExposedHeaders(Collections.singletonList("Authorization"));

                                                           return config;
                                                       }
                                                   }
            ));

        return http.build();
    }


}
