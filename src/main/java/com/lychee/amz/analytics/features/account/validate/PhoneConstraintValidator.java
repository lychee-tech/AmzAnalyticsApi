package com.lychee.amz.analytics.features.account.validate;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneConstraintValidator implements ConstraintValidator<Phone,String > {
    @Override
    public void initialize(Phone phone){

    }

    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext txt){
        if(phoneField == null){
            return true;
        }
        return phoneField.matches("[0-9]*");
    }
}
