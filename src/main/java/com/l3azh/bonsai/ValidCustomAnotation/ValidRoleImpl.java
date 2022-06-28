package com.l3azh.bonsai.ValidCustomAnotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ValidRoleImpl implements ConstraintValidator<ValidRole, String> {

    private List<String> listStringConstraint;

    @Override
    public void initialize(ValidRole constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        listStringConstraint = new ArrayList<>();
        Class<? extends Enum<?>> enumClass = constraintAnnotation.bonsaiEnumClass();
        @SuppressWarnings("rawtypes")
        Enum[] enumValArr = enumClass.getEnumConstants();
        for (@SuppressWarnings("rawtypes") Enum enumVal : enumValArr) {
            listStringConstraint.add(enumVal.toString().toUpperCase());
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return listStringConstraint.contains(value.toUpperCase());
    }
}
