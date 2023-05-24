package dev.memo.movieist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.memo.movieist.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
// For Spring to be able to Inject all the Beans that are created here somewhere
// else.
public class ApplicationConfig {

    private final UserRepository repository;
    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;
    private AuthenticationProvider authenticationProvider;
    private PasswordEncoder passwordEncoder;

    @Bean
    // Search for Username in the DB...
    public UserDetailsService userDetailsService() {
        // Using lambda expression instead of implementing the method loadUserByUsername
        // from Userrepository
        if (userDetailsService == null) {

            userDetailsService = username -> repository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        }
        return userDetailsService;
    };

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        if (authenticationManager == null) {
            authenticationManager = config.getAuthenticationManager();
        }
        return authenticationManager;
    }

    // the Data access Object that is Resposible to fetch the details of the user
    // and also encode the password.
    @Bean
    public AuthenticationProvider authenticationProvider() {
        if (authenticationManager == null) {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(userDetailsService());
            authProvider.setPasswordEncoder(passwordEncoder());
            authenticationProvider = authProvider;
        }
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        if (passwordEncoder == null) {
            passwordEncoder = new BCryptPasswordEncoder();
        }
        return passwordEncoder;
    }
}