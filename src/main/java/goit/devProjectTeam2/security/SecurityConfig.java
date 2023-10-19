package goit.devProjectTeam2.security;


import goit.devProjectTeam2.entity.User;
import goit.devProjectTeam2.security.jwt.JwtRequestFilter;
import goit.devProjectTeam2.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;
import java.util.Optional;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(login -> login.loginPage("/login")
                        .defaultSuccessUrl("/v1/allLinks", true)
                        .failureUrl("/login"))
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/v1/token/**")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/token/**")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/user/registration")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/user/login")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/login")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/registration")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui/**")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/v3/api-docs/**")).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery("select username, password, enabled from users where username=?");
        users.setAuthoritiesByUsernameQuery("select username, role from users where username=?");
        return users;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    public Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public User getAuthenticatedUser() throws UsernameNotFoundException {

        Authentication auth = getAuth();
        if (auth != null && auth.isAuthenticated()) {
            Optional<User> userByUsername = userRepository.findUserByUsername(auth.getName());
            if (userByUsername.isPresent()) {
                return userByUsername.get();
            }
        }
        throw new UsernameNotFoundException("User not found");
    }

}





