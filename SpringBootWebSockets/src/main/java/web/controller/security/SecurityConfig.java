package web.controller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import web.service.UserService;

import java.util.List;

@Configuration
public class SecurityConfig {

	@Autowired
	UserService userService;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests(authConfig -> {
			authConfig.requestMatchers("/", "/login", "/login-error", "/main.js","/main.css", "/loginstyle.css").permitAll();
			authConfig.anyRequest().authenticated();
		})
		.sessionManagement(session -> session
				.maximumSessions(1)
				.maxSessionsPreventsLogin(true)
				.expiredUrl("/login?expired"))
		.formLogin(login -> login
				.loginPage("/login")
				.failureUrl("/login-error"))
		.logout(logout -> logout
				.logoutSuccessUrl("/")
				.clearAuthentication(true)
				.deleteCookies("JSESSIONID"));
	return http.build();
	}
		
	@Bean
	UserDetailsService userDetailsService() {
		List<UserDetails> users = userService.getAllUsers().stream().map(it -> User.builder()
				.username(it.getUsername())
				.password("{noop}"+it.getPassword())
				.roles("USER")
				.build()
		).toList();
		return new InMemoryUserDetailsManager(users);
	}
}
