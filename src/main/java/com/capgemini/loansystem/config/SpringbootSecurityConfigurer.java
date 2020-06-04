//package com.capgemini.loansystem.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.capgemini.loansystem.BootAuthenticationEntryPoint;
//import com.capgemini.loansystem.filter.CustomUsernamePasswordAuthenticationFilter;
//import com.capgemini.loansystem.handlers.MyLogoutSuccessHandler;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) 
//public class SpringbootSecurityConfigurer extends WebSecurityConfigurerAdapter{
//	
//
//	@Autowired
//	private BootAuthenticationEntryPoint bootAuthEntryPoint;
//	
//	@Autowired
//	private AuthenticationSuccessHandler authenticationSuccessHandler;
//	
//	
//	@Autowired
//	private MyLogoutSuccessHandler myLogoutSuccessHandler;
//	
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return new BCryptPasswordEncoder(12);
//	}
//	
//	@Bean
//	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
//		return new SimpleUrlAuthenticationFailureHandler();
//	}
//
//	
//	@Bean
//	public UsernamePasswordAuthenticationFilter  getUsernamePasswordAuthenticationFilter() throws Exception {
//		CustomUsernamePasswordAuthenticationFilter filter = new CustomUsernamePasswordAuthenticationFilter();
//		filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
//		filter.setAuthenticationFailureHandler(getAuthenticationFailureHandler());
//		filter.setAuthenticationManager(authenticationManager());
//		return filter;
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		
//		http.csrf().disable()
//		.exceptionHandling()
//		.authenticationEntryPoint(bootAuthEntryPoint)
//		.and()
//		.authorizeRequests()
//		.antMatchers("/api/getuser").hasAnyRole("USER","ADMIN")
//		.and()
//		.authorizeRequests()
//		.antMatchers("/api/add").hasRole("ADMIN")
//		.and()
//		.authorizeRequests()
//		.antMatchers("/api/loansystem").permitAll()
//		.and()
//		.cors()
//		.and()
//		.addFilterBefore(getUsernamePasswordAuthenticationFilter(), CustomUsernamePasswordAuthenticationFilter.class)
//		.logout()
//		.logoutSuccessHandler(myLogoutSuccessHandler);
//	}
//	
//	
//}
