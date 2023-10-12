//package goit.devProjectTeam2.security;
//
//import com.example.englishclub.security.jwt.JwtRequestFilter;
//import com.example.englishclub.user.entity.UserEntity;
//import com.example.englishclub.user.exception.UserNotFoundException;
//import com.example.englishclub.user.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.sql.DataSource;
//import java.util.Optional;
//
//@EnableWebSecurity
//@Configuration
//public class SecurityConfig {
//	@Autowired
//	private JwtRequestFilter jwtRequestFilter;
//	@Autowired
//	private AuthenticationConfiguration authenticationConfiguration;
//
//	@Autowired
//	private UserRepository userRepository;
//
////	@Bean
////	public CorsFilter corsFilter() {
////		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////		CorsConfiguration config = new CorsConfiguration();
////		config.setAllowCredentials(true);
////		config.addAllowedOrigin("http://localhost:8089");
////		config.addAllowedHeader("*");
////		config.addAllowedMethod("*");
////		source.registerCorsConfiguration("/**", config);
////		return new CorsFilter(source);
////	}
//
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//		http
//				.cors(AbstractHttpConfigurer::disable)
//				.csrf(AbstractHttpConfigurer::disable)
//				.authorizeHttpRequests((authz) -> authz
//						.requestMatchers("/api/v1/user/registration").permitAll()
//						.requestMatchers("/api/v1/user/login-user").permitAll()
//						.requestMatchers("/api/v1/user/all").permitAll()
//						.anyRequest().authenticated()
//				)
//				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.httpBasic(AbstractHttpConfigurer::disable)
//				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//		return http.build();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//
//	@Bean
//	public UserDetailsManager users(DataSource dataSource) {
//		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//		users.setUsersByUsernameQuery("select username, passwords, enabled from users where username=?");
//		users.setAuthoritiesByUsernameQuery("select username, role from users where username=?");
//		return users;
//	}
//
//	@Bean
//	public AuthenticationManager authenticationManager () throws Exception {
//		return authenticationConfiguration.getAuthenticationManager();
//	}
//
//
//	public Authentication getAuth(){
//		return SecurityContextHolder.getContext().getAuthentication();
//	}
//
//	public UserEntity getAuthenticatedUser() throws UserNotFoundException {
//
//		Authentication auth = getAuth();
//		if (auth != null && auth.isAuthenticated()) {
//			Optional<UserEntity> userByUsername = userRepository.findUserEntityByUsername(auth.getName());
//			if (userByUsername.isPresent()) {
//				return userByUsername.get();
//			}
//		}
//		throw new UserNotFoundException("User  not found");
//	}
//
//}
//
//
//
//
//
