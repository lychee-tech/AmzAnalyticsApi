package com.leechi.amz.analytics.features.account.validate;

import com.leechi.amz.analytics.features.account.dto.UpdateAccountRequest;
import com.leechi.amz.analytics.features.account.entity.UserEntity;
import com.leechi.amz.analytics.features.account.repo.UserRepo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailExceptItselfValidator implements ConstraintValidator<UniqueEmailExceptItself,UpdateAccountRequest>{
    private UserRepo userRepo;

    public UniqueEmailExceptItselfValidator(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public void initialize(UniqueEmailExceptItself uniqueEmailExceptItself){};

    public boolean isValid(UpdateAccountRequest request, ConstraintValidatorContext cxt){
      return true;
    };
}
