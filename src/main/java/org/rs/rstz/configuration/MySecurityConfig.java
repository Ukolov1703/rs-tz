package org.rs.rstz.configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@EnableWebSecurity

public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserBuilder userBuilder = User.withDefaultPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser(userBuilder
                        .username("role1")
                        .password("role1")
                        .roles("ROLE1"))
                .withUser(userBuilder
                        .username("role2")
                        .password("role2")
                        .roles("ROLE2"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/card/get-balance/{number}").hasRole("ROLE1")
                .antMatchers("/history/find-history-by-number/{number}").hasRole("ROlE1")
                .antMatchers("/history/change-balance/{id}").hasRole("ROLE2")
                .antMatchers("/card/add/{id}/{value}").hasRole("ROLE2")
                .antMatchers("/card/write-off/{id}/{value}").hasRole("ROLE2").and().formLogin().permitAll();
    }
}
