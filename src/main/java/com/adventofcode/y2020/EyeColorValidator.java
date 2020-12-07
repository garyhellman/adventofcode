package com.adventofcode.y2020;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// //  @EyeColorTypeSubset(anyOf = {EyeColorType.amb, EyeColorType.blu, EyeColorType.gry, EyeColorType.brn,
////      EyeColorType.hzl, EyeColorType.oth})
public class EyeColorValidator implements ConstraintValidator <EyeColorConstraint, String> {

  private Pattern pattern;
  private List<String> acceptedValues;

  @Override
  public void initialize(EyeColorConstraint eyeColorConstraint) {
//    System.out.println("Eye Color Validator initialize");
    acceptedValues = Stream.of(eyeColorConstraint.enumClass().getEnumConstants())
        .map(Enum::name)
        .collect(Collectors.toList());
//    System.out.println("Eye Color Validator initialize: " + acceptedValues);
  }

  @Override
  public boolean isValid(String eyeColorField,
                         ConstraintValidatorContext cxt) {
//    System.out.println("Eye Color Validator isValid: " + eyeColorField);
    if(eyeColorField == null) return false;
    if(!eyeColorField.matches("^([a-z]{3})$")) return false;
    return acceptedValues.contains(eyeColorField.toString());
//    return true;
//    return contactField != null && contactField.matches("[0-9]+")
//        && (contactField.length() > 8) && (contactField.length() < 14);
  }
}
