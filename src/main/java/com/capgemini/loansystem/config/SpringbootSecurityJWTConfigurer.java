package com.capgemini.loansystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.capgemini.loansystem.BootAuthenticationEntryPoint;
import com.capgemini.loansystem.JwtRequestFilter;
import com.capgemini.loansystem.filter.CustomUsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringbootSecurityJWTConfigurer extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	@Autowired
	private BootAuthenticationEntryPoint bootAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService);

	} // End of configureGlobal()

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

	 http.cors().and().csrf().disable()
		.authorizeRequests().antMatchers("/api/login", "/api/users").permitAll()
		.and()
		.authorizeRequests().antMatchers("/api/add-loans-cus", "/api/view-loanprocess-cus/{loanId}", "/api/change-password-cus", "/api/customers", "/api/add-customers-cus").hasRole("CUSTOMER")
		.and()
		.authorizeRequests().antMatchers("/api/get-all-loans-lad", "/api/approve-loan-lad", "/api/get-all-customers-lad", "/api/get-all-loanplans-lad", "/api/add-loanplans-lad", "/api/delete-loanplan-lad/{loanId}", "/api/delete-loan/{loanId}", "/api/update-loan").hasRole("LAD")
		.and()
		.authorizeRequests().antMatchers("/api/add-loans-cus", "/api/get-all-users", "/api/get-all-customers", "/api/get-all-loanplans", "/api/get-all-loans", "/api/loanPlans/{pageNo}/{itemsPerPage}", "/api/loanPlans/{pageNo}/{itemsPerPage}/{fieldName}").hasRole("ADMIN")
		.and()
		.authorizeRequests().antMatchers("/api/delete-user/{userId}", "/api/delete-customer/{userId}", "/api/delete-loans/{loanId}", "/api/delete-loanplan/{loanId}", "/api/update-user", "/api/approve-loan", "/api/customers/{pageNo}/{itemsPerPage}", "/api/customers/{pageNo}/{itemsPerPage}/{fieldName}", "/api/loans/{pageNo}/{itemsPerPage}", "/api/loans/{pageNo}/{itemsPerPage}/{fieldName}").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(bootAuthenticationEntryPoint)
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	 http.addFilterBefore(jwtRequestFilter, CustomUsernamePasswordAuthenticationFilter.class);
	}// End of configure()

}// end of class
