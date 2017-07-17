package com.lychee.amz.analytics.features.authentication.config;

import com.lychee.amz.analytics.features.authentication.filter.JwtTokenFilter;
import com.lychee.amz.analytics.features.authentication.filter.LoginFilter;
import com.lychee.amz.analytics.features.authentication.service.AuthUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalAuthentication
@ComponentScan
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthUserDetailService authUserDetailService;
    @Autowired
    private LycheeAuthenticationEntryPoint lycheeAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserDetailService).passwordEncoder(passwordEncoder());
    }


    /**
     * configure authentication
     * there are three  situations
     *   1. when url is post /login, we will use http basic authentication
     *   2. when url is post /api/accounts, we will treat the user as anonymous
     *   3. whe  url is /api/**, we will use jwt token authentication
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(lycheeAuthenticationEntryPoint)
                .and()
                .addFilterBefore(new LoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtTokenFilter("/api/**", authenticationManager()), UsernamePasswordAuthenticationFilter.class);


    }


}
