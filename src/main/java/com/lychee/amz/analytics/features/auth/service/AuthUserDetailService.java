package com.lychee.amz.analytics.features.auth.service;

import com.lychee.amz.analytics.advice.ErrorMessageAdvice;
import com.lychee.amz.analytics.features.account.entity.UserEntity;
import com.lychee.amz.analytics.features.account.model.Roles;
import com.lychee.amz.analytics.features.account.repo.UserRepo;
import com.lychee.amz.analytics.features.auth.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ErrorMessageAdvice errorMessageAdvice;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        String pwd=  new BCryptPasswordEncoder().encode("user");

        return new User("user", pwd,true,true,true,true, AuthorityUtils.createAuthorityList(Roles.user));

       /*
        UserEntity user = userRepo.findByLogin(login);


        if (user == null) {
            throw new UserNotFoundException(errorMessageAdvice.authUserNotFound);
        }

        return new User(user.getLogin(),user.getPassword(),true,true,true,true, AuthorityUtils.createAuthorityList(Roles.user));
        */
    }
}
