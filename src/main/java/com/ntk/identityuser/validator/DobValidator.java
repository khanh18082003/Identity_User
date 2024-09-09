package com.ntk.identityuser.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DobValidator implements ConstraintValidator<DobConstrainst, Integer> {

  private int min;

  @Override
  public void initialize(DobConstrainst constraintAnnotation) {
    this.min = constraintAnnotation.min();
  }

  @Override
  public boolean isValid(Integer value, ConstraintValidatorContext context) {
    return value >= min;
  }

}
