package com.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.demo.repositories.UserRepository;

/**
 * @author ekansh
 * @since 30/3/16
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserRepository userRepository;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceBean());
	}

	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return new SSUserDetailsService(userRepository);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http.authorizeRequests().antMatchers("/", "/drugSearchFromIndex", "/index/drugByGeneric/*", "/index/drugByBrand/*","/index/image/*").permitAll().
		        and().formLogin().loginPage("/login").permitAll().	   
		        and().authorizeRequests().antMatchers("/**/favicon.ico") .permitAll()           
                .and().authorizeRequests().antMatchers("/webjars/**").permitAll()
                .and().authorizeRequests().antMatchers("/static/css").permitAll()
                .and().authorizeRequests().antMatchers("/specializations").permitAll()          
                .and().authorizeRequests().antMatchers("/doctorsList/**").permitAll()
                .and().authorizeRequests().antMatchers("/drug/delete/**","/drug/edit/**","/drug/saveDrug/**","/drug/new/**").hasAnyAuthority("ADMIN","MANAGER")
                .and().authorizeRequests().antMatchers("/doctor/delete/**","/doctor/edit/**","/doctor/SaveDoctor/**","/doctor/new/**").hasAnyAuthority("ADMIN","MANAGER")
                .and().authorizeRequests().antMatchers("/admin/**").hasAnyAuthority("ADMIN","MANAGER")                
                .and().authorizeRequests().antMatchers("/js").permitAll()
				.and().authorizeRequests().anyRequest().authenticated().
				and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");

	}

}