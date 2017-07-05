package com.lychee.amz.analytics.features.auth.service;

import com.lychee.amz.analytics.advice.ErrorMessageAdvice;
import com.lychee.amz.analytics.features.account.entity.UserEntity;
import com.lychee.amz.analytics.features.account.model.Roles;
import com.lychee.amz.analytics.features.account.repo.UserRepo;
import com.lychee.amz.analytics.features.auth.exception.UserNotFoundException;
import com.lychee.amz.analytics.features.auth.model.TokenUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ErrorMessageAdvice errorMessageAdvice;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByLogin(login);

        if (user == null) {
            throw new UserNotFoundException(errorMessageAdvice.authUserNotFound);
        }

        return new TokenUser(user.getLogin(),user.getPassword(), AuthorityUtils.createAuthorityList(Roles.user));

    }
}
