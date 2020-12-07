package com.adventofcode.y2020;

import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EyeColorTypeSubSetValidator implements ConstraintValidator<EyeColorTypeSubset, EyeColorType> {
  private EyeColorType[] subset;

  @Override
  public void initialize(EyeColorTypeSubset constraint) {
    this.subset = constraint.anyOf();
  }

  @Override
  public boolean isValid(EyeColorType value, ConstraintValidatorContext context) {
//    System.out.println("EyeColorTypeSubSetValidator:isValid:: " + value.getName() + " " + Arrays.asList(subset).toString());
    return value == null || Arrays.asList(subset).contains(value);
  }
}


