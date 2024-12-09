package it.unical.demacs.informatica.ristoranti.config.security;

import it.unical.demacs.informatica.ristoranti.controller.UserDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Disabilita CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/login").permitAll() // Accesso senza autenticazione
                        .requestMatchers("/api/open/**").permitAll() // Accesso senza autenticazione
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/auth/**").authenticated() // Richiede autenticazione
                        .anyRequest().authenticated() // Tutte le altre richieste richiedono autenticazione
                )
                .exceptionHandling(ex -> ex
                        // Restituisce 401 Unauthorized per richieste non autorizzate
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                        // Restituisce 403
                        .accessDeniedHandler(new UserDeniedHandler())
                )
                .formLogin(form -> form
                        .loginProcessingUrl("/api/login") // Endpoint per il login
                        .successHandler((req, res, auth) -> res.setStatus(200)) // Gestione successo login
                        .failureHandler((req, res, ex) -> res.setStatus(401)) // Gestione errore login
                )
                .logout(logout -> logout
                        .logoutUrl("/api/logout") // Endpoint per il logout
                        .logoutSuccessHandler((req, res, auth) -> res.setStatus(200)) // Gestione successo logout
                );
        return http.build();
    }

    /*
    @Bean
    public UserDetailsService userDetailsService() {
        // Utente hard-coded per test
        var user = User.withUsername("admin@")
                .password(passwordEncoder().encode("pass"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
    */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Utilizza BCrypt per la codifica delle password
    }
}
