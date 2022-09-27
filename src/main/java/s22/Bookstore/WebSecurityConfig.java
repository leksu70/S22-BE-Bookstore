package s22.Bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import s22.Bookstore.service.UserDetailsServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {

		return http.authorizeRequests(auth -> {
			// css folder is accessible for all
			auth.antMatchers("/css/**").permitAll();
			auth.antMatchers("/h2-console/**").permitAll();
			auth.antMatchers("/h2-console").permitAll();
			// hasRole is not working with H2
			// auth.antMatchers("/owners/**").hasRole("ADMIN");
			// if user's role is ADMIN s/he has access to all pages "under" owners
			auth.antMatchers("/owners/**").hasAuthority("ADMIN");
			// every http request will be authenticated
			auth.anyRequest().authenticated();
		}).csrf().ignoringAntMatchers("h2-console").and()
				// tells where to go after successful login
				.formLogin().defaultSuccessUrl("/main", true).and()
				// Logout is permitted for all users
				.logout().permitAll().and()

				//
				.httpBasic(Customizer.withDefaults()).build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}