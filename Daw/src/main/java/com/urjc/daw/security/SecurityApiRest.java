package com.urjc.daw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@Order(1)
public class SecurityApiRest extends WebSecurityConfigurerAdapter {

	@Autowired
	public AuthenticationProviderUser authenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/api/**");

		// here urls need authentication
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/user/{id}");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/user/");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/concept/{id}");

		
		// urls not need authentication
		http.authorizeRequests().anyRequest().permitAll();

		// disable csrf
		http.csrf().disable();

		http.httpBasic();
		
		//do not redirect when logout
		http.logout().logoutSuccessHandler((rq, rs, a) -> {
		});

	}

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManager) throws Exception {
		authenticationManager.authenticationProvider(authenticationProvider);
	}

}
