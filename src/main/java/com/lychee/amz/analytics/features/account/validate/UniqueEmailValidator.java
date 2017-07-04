package com.lychee.amz.analytics.features.account.validate;

import com.lychee.amz.analytics.features.account.dto.UpdateAccountRequest;
import com.lychee.amz.analytics.features.account.entity.UserEntity;
import com.lychee.amz.analytics.features.account.repo.UserRepo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,UpdateAccountRequest> {
    private UserRepo userRepo;

    public UniqueEmailValidator(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public void initialize(UniqueEmail email){};

    public boolean isValid(UpdateAccountRequest request, ConstraintValidatorContext cxt){
        Integer userId = request.getId();
        if(userId != null){
            UserEntity user = userRepo.findOne(userId);
            if(request.getEmail().equals(user.getEmail())){
                return true;
            }
        }

        return !userRepo.existsByEmail(request.getEmail());
    }
}
