package com.sh.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import jakarta.persistence.Column;
import jakarta.persistence.UniqueConstraint;

@Configuration
@EnableWebSecurity

public class AppConfig
{
//	@Column(uniqueConstraints = {@UniqueConstraint(columnNames = {"firstName" , "lastname"})})
//	private String fullName;
	@Bean
	public UserDetailsService userDetailsService()
	{
		UserDetails userDetails = User.builder() 
				.username("Hari")
                .password(passwordEncoder().encode("sV!Sh#1#T"))
                .roles("ADMIN")
                .build(); 
				
        return new InMemoryUserDetailsManager(userDetails); 
	}
	@Bean
	 public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
	        return builder.getAuthenticationManager();
	    }
	 
	 /*@GetMapping("/")
	 public String getLoggedInUser(Principal principal)
	 {
		 return principal.getName(); // gets the user that is logged in
	 }*/
	 /*
	  * JWT - JSON WEB TOKEN
	  * x . y . z
	  * x = header = Algo + TokenType 
	  * y = Payload (data)
	  * z = signature (both above , secret key)
	  * 
	  * 
	  * */
}
