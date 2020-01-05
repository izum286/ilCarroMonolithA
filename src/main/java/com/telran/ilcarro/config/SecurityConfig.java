package com.telran.ilcarro.config;

import com.telran.ilcarro.repository.UserDetailsRepository;
import com.telran.ilcarro.service.user.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SecurityConfig implementation
 * BasicAuth - off. Need to test and switch on
 *
 * @author Konkin Anton
 * @date 23.12.2019
 */
//@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    UserDetailsRepository repository;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(repository);
    }

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Configuration
    static class SecurityAdapter extends WebSecurityConfigurerAdapter {

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers(HttpMethod.POST,"/registration");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .csrf().disable()
                    .cors().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
//                    .antMatchers(HttpMethod.POST, "/registration").permitAll()
                    .antMatchers(HttpMethod.GET, "/comments").permitAll()
                    .antMatchers(HttpMethod.GET, "/filters").permitAll()
                    .antMatchers("/user/**").authenticated()
                    .antMatchers("/comment/**").authenticated()
                    .antMatchers("/car/**").authenticated()
                    .anyRequest().permitAll()
                    .and()
                    .httpBasic()
            ;

        }

    }
}
