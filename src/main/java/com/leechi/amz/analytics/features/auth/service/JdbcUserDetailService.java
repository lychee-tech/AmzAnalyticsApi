package com.leechi.amz.analytics.features.auth.service;

import com.leechi.amz.analytics.features.account.entity.UserEntity;
import com.leechi.amz.analytics.features.account.repo.UserRepo;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class JdbcUserDetailService implements UserDetailsService {
    private UserRepo userRepo;

    public JdbcUserDetailService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            UserEntity user = userRepo.findByLogin(login);
            if (user == null) {
                return null;
            }
            return new User(user.getLogin(),user.getPassword(),true,true,true,true, AuthorityUtils.createAuthorityList("ROLE_USER"));
        }catch (Exception ex){
            throw new UsernameNotFoundException("User Not Found");
        }
    }
}
