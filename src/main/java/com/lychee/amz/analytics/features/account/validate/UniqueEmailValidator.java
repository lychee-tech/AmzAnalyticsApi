package com.lychee.amz.analytics.features.account.validate;

import com.lychee.amz.analytics.advice.ErrorMessageAdvice;
import com.lychee.amz.analytics.features.account.dto.UpdateAccountRequest;
import com.lychee.amz.analytics.features.account.entity.UserEntity;
import com.lychee.amz.analytics.features.account.repo.UserRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * this class support validating user creation and user update
 *   when creating a user, userId is null
 *   when updating a usr,  userId is not null
 *
 */
@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,UpdateAccountRequest> {
    private String propertyPath;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ErrorMessageAdvice errorMessageAdvice;

    public UniqueEmailValidator(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public void initialize(UniqueEmail email){
        propertyPath = email.propertyPath();
    }

    public boolean isValid(UpdateAccountRequest request, ConstraintValidatorContext ctx){
        //this one only validate uniqueness
        if (StringUtils.isBlank(request.getEmail())) {
            return true;
        }

        UserEntity entity = userRepo.findByEmail(request.getEmail());
        boolean isValid;


        if (request.getId() == null) {
            isValid = entity == null;
        } else {
            isValid = entity == null ||  (int) request.getId() == entity.getId();
        }

        if (!isValid) {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(errorMessageAdvice.duplicateEmail).addPropertyNode(this.propertyPath).addConstraintViolation();
        }

        return isValid;
    }
}



