package com.leechi.amz.analytics.features.auth;

import com.leechi.amz.analytics.features.account.entity.UserEntity;
import com.leechi.amz.analytics.features.account.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalAuthentication
@ComponentScan
public class JdbcSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired private UserRepo userRepo;

    @Bean
    public UserDetailsService userDetailsService(){
        return new JdbcUserDetailService(userRepo);
    }
    @Autowired private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        UserEntity user = new UserEntity();
        user.setLogin("yao");
        user.setFirstName("luyuan");
        user.setLastName("Nie");
        user.setEmail("test@gmail.com");
        user.setPassword(new BCryptPasswordEncoder().encode("123"));
        user.setPhone("3dfsf3");
        userRepo.save(user);
        http.cors().and().csrf().disable()
                .authorizeRequests().antMatchers(HttpMethod.POST,"/accounts").permitAll().anyRequest().authenticated().and().httpBasic();
    }
}
