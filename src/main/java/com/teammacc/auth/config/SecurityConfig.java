package com.teammacc.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.teammacc.auth.jwt.JwtConfigurer;
import com.teammacc.auth.jwt.JwtTokenProvider;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

	private final JwtTokenProvider jwtTokenProvider;

	private static final String[] PUBLIC_MATCHERS = {
			"/v2/api-docs/**",
			"/v3/api-docs/**",
			"/api-docs/**",
			"/swagger-ui.html",
			"/swagger-ui/**",
			"/index.html",
			"/register",
			"/login"
	};

	@Autowired
	public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(); 
		return bCryptPasswordEncoder;
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean()  throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http    .cors().and()
				.httpBasic().disable()
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers(PUBLIC_MATCHERS).permitAll()
				.anyRequest().authenticated()
				.and()
				.apply(new JwtConfigurer(jwtTokenProvider));
	}

	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.cors().and()
			    .httpBasic().disable()
			    .csrf().disable()
			    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			    .authorizeRequests()
				//.antMatchers(PUBLIC_MATCHERS).permitAll()
			    .antMatchers("/login/*").permitAll()
			    .anyRequest().authenticated().and()
			    .apply(new JwtConfigurer(jwtTokenProvider));
		 
		http.authorizeRequests()
	      .anyRequest()
	      .authenticated();

	}*/
}





