package ru.vsu.cs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import ru.vsu.cs.Authentication.TokenAuthenticationFilter;
import ru.vsu.cs.Authentication.TokenAuthenticationManager;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private WebFilterCors webFilterCors;

    @Autowired
    private TokenAuthenticationManager tokenAuthenticationManager;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/user/login",
                "/user/registration",
                "/order/custom/creation",
                "/region/list");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
              //  .addFilterBefore(webFilterCors, CorsFilter.class)
                .addFilterAfter(buildTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()//.antMatchers("/user/**").permitAll()
                .antMatchers("/progress/**").hasAuthority("USER")
                .anyRequest().authenticated()
        ;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private TokenAuthenticationFilter buildTokenAuthenticationFilter() {
        TokenAuthenticationFilter tokenAuthenticationFilter = new TokenAuthenticationFilter();
        tokenAuthenticationFilter.setAuthenticationManager(tokenAuthenticationManager);
        return tokenAuthenticationFilter;
    }
}
