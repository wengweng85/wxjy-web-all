package com.insigma.validator.impl;

import com.insigma.validator.Idcard;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class IdcardValidator implements ConstraintValidator<Idcard, String> {

    private static final String REG = "/^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$/";

    private Pattern pattern = Pattern.compile(REG);

    @Override
    public void initialize(Idcard idcard) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (null == s || s.length() == 0) {
            return true;
        }
        return pattern.matcher(s).matches();
    }
}
