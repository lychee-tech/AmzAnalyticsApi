package com.leechi.amz.analytics.features.account.validate;

import com.leechi.amz.analytics.features.account.repo.UserRepo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String> {
    private UserRepo userRepo;

    public UniqueEmailValidator(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public void initialize(UniqueEmail email){};

    public boolean isValid(String email, ConstraintValidatorContext cxt){
        return !userRepo.existsByEmail(email);
    }
}
