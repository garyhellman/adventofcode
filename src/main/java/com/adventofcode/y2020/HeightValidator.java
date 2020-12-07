package com.adventofcode.y2020;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HeightValidator implements ConstraintValidator<HeightConstraint, String> {

  //   * hgt (Height) - a number followed by either cm or in:
//      * If cm, the number must be at least 150 and at most 193.
//      * If in, the number must be at least 59 and at most 76.
  @Override
  public void initialize(HeightConstraint height) {
//    System.out.println("Height Validator initialize");
  }

  @Override
  public boolean isValid(String heightField,
                         ConstraintValidatorContext cxt) {
    if(heightField == null) return false;

    int heightValue = 0;
    String heightUnit;
//    System.out.println("heightField: " + heightField + " " + heightField.length() + " " + heightField.matches("^\\d" +
//        "{2,3}cm|in$"));
    Pattern p = Pattern.compile("^(\\d{2,3})(cm|in)$");
    Matcher m = p.matcher(heightField);
    // if an occurrence if a pattern was found in a given string...
    if (m.find()) {
      // ...then you can use group() methods.
//      System.out.println(m.group(0)); // whole matched expression
//      System.out.println(m.group(1)); // first expression from round brackets (Testing)
//      System.out.println(m.group(2)); // second one (123)
//      System.out.println(m.group(3)); // third one (Testing)
      heightValue = Integer.parseInt(m.group(1));
      heightUnit = m.group(2);
      if (heightUnit.equalsIgnoreCase("cm")) {
        if (heightValue >= 150 && heightValue <= 193) {
          return true;
        }
      } else if (heightUnit.equalsIgnoreCase("in")) {
        if (heightValue >= 59 && heightValue <= 76) {
          return true;
        }
      }
    }
    return false;
  }


//    return heightField != null && heightField.matches("^\\d{2,3}cm|in$")
//        && (heightField.length() > 3) && (heightField.length() < 6);
}

