package com.secure.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
	
	@Autowired
	public UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationProvider authProvider() {
		
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return provider;
		
	}
	
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        		.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
                	registry.requestMatchers("/register-process").permitAll();
                	registry.requestMatchers("/register/**").permitAll();
                	registry.requestMatchers("/images/*", "/css/", "/js/", "/WEB-INF/views/*").permitAll();
                	registry.anyRequest().authenticated();
                })
                .formLogin(httpSecurityFormLoginConfigurer -> {
                	httpSecurityFormLoginConfigurer.loginPage("/login")
                	.defaultSuccessUrl("/home",true)
                	.permitAll();
                	
                })
                .logout(logout -> {
                    logout
                        .logoutUrl("/logout") 
                        .logoutSuccessUrl("/login") 
                        .invalidateHttpSession(true) 
                        .deleteCookies("JSESSIONID"); 
                });
        
        return http.build();
    }
}
