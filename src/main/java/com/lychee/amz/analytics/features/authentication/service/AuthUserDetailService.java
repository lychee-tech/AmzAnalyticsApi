package com.lychee.amz.analytics.features.authentication.service;

import com.lychee.amz.analytics.features.account.entity.UserEntity;
import com.lychee.amz.analytics.features.account.model.Roles;
import com.lychee.amz.analytics.features.account.repo.UserRepo;
import com.lychee.amz.analytics.features.authentication.exception.AuthUserNotFoundException;
import com.lychee.amz.analytics.features.authentication.model.AuthUser;
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


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByEmail(email);

        if (user == null) {
            throw new AuthUserNotFoundException();
        }

        AuthUser authUser = new AuthUser(user.getEmail(),user.getEncryptedPassword(), AuthorityUtils.createAuthorityList(Roles.user));
        authUser.setDisplayName(user.getDisplayName());
        authUser.setId(user.getId());
        return authUser;
    }
}
