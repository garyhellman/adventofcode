package com.adventofcode.y2020;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HairColorValidator implements ConstraintValidator<HairColorConstraint, String> {

  //  Hair Color) - a # followed by exactly six characters 0-9 or a-f.
  @Override
  public void initialize(HairColorConstraint hairColor) {
//    System.out.println("Height Validator initialize");
  }

  @Override
  public boolean isValid(String hairColorField,
                         ConstraintValidatorContext cxt) {
    if(hairColorField == null) return false;

//    int hairColorValue = 0;
//    String hairColorUnit;
//    System.out.println("hairColorField: " + hairColorField + " " + hairColorField.length() + " " + hairColorField.matches("^\\d" +
//        "{2,3}cm|in$"));
    Pattern p = Pattern.compile("^#([0-9,a-f]{6})$");
    Matcher m = p.matcher(hairColorField);
    // if an occurrence if a pattern was found in a given string...
    if (m.find()) {
      // ...then you can use group() methods.
//      System.out.println(m.group(0)); // whole matched expression
//      System.out.println(m.group(1)); // first expression from round brackets (Testing)
//      System.out.println(m.group(2)); // second one (123)
//      System.out.println(m.group(3)); // third one (Testing)
//      hairColorValue = Integer.parseInt(m.group(1));
//      hairColorUnit = m.group(2);
      return true;
    }
    return false;
  }


//    return hairColorField != null && hairColorField.matches("^\\d{2,3}cm|in$")
//        && (hairColorField.length() > 3) && (hairColorField.length() < 6);
}

