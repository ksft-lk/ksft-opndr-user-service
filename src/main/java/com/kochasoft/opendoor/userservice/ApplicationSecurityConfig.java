package com.kochasoft.opendoor.userservice;

import com.kochasoft.opendoor.userservice.interceptor.SecurityFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
       .cors().and()
       .csrf().disable()
       .addFilterBefore(new SecurityFilter(), UsernamePasswordAuthenticationFilter.class)
       .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
       .authorizeRequests()
       .antMatchers("/**").permitAll()
       .anyRequest().authenticated();

       http.headers().frameOptions().disable();
   } 

   @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers( 
            "/v1/users/**/token",
            "/v1/users/uid/**",
            "/v1/users/id/**",
            "/v1/users/mobile/**",
            "/v1/users");
    }
}
