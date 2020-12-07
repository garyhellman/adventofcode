package com.adventofcode.y2020;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PassportIdValidator implements ConstraintValidator<PassportIdConstraint, String> {

  private PassportIdConstraint field;

  public void initialize(PassportIdConstraint constraintAnnotation) {
    this.field = constraintAnnotation;
    System.out.println(field.payload().length);
  }

  @Override
  public boolean isValid(String passportIdField,
                         ConstraintValidatorContext cxt) {
    return passportIdField != null && passportIdField.matches("^[0-9]{9}$")
        && (passportIdField.length() == 9);
  }
}
