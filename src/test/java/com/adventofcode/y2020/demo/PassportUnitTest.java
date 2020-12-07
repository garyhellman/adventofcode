package com.adventofcode.y2020.demo;


import static org.assertj.core.api.Assertions.assertThat;

import com.adventofcode.y2020.EyeColorType;
import com.adventofcode.y2020.LocaleAwareUnitTest;
import com.adventofcode.y2020.Passport;
import com.adventofcode.y2020.PassportBuilder;
import com.adventofcode.y2020.PassportType;
import java.util.Set;
import java.util.function.Predicate;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import com.baeldung.javaxval.LocaleAwareUnitTest;
//import org.junit.BeforeClass;
//import org.junit.Test;

public class PassportUnitTest extends LocaleAwareUnitTest {

  private static Validator validator;

  @BeforeEach
  public void setupValidatorInstance() {
    validator = Validation.buildDefaultValidatorFactory()
        .getValidator();
  }

  @Test
  public void whenAllAcceptable_thenShouldNotGiveConstraintViolations() {
    Passport passport = new PassportBuilder().createPassport();
    passport.setPassportTypeOfSubset(PassportType.NEW);
    Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
    assertThat(violations).isEmpty();
  }

  @Test
  public void whenAllNull_thenOnlyNotNullShouldGiveConstraintViolations() {
    Passport passport = new PassportBuilder().createPassport();
    Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
    assertThat(violations.size()).isEqualTo(1);

    assertThat(violations).anyMatch(havingPropertyPath("passportTypeOfSubset").and(havingMessage("must not be null")));
  }

  @Test
  public void whenAllInvalid_thenViolationsShouldBeReported() {
    Passport passport = new PassportBuilder().createPassport();
    passport.setPassportTypeString("invalid");
    passport.setPassportTypeOfSubset(PassportType.DEFAULT);
    passport.setPassportTypeMatchesPattern(PassportType.OLD);

    passport.setBirthYear("2002");
    passport.setIssueYear("2010");
    passport.setExpirationYear("2022");
    passport.setHeight("183cm");
    passport.setHairColor("gry");
    passport.setEyeColor("oth");
//    passport.setEyeColor(EyeColorType.BLU);
    passport.setPassportID("123456");
    passport.setCountryID("147");

    Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
    assertThat(violations.size()).isEqualTo(1);

    assertThat(violations).anyMatch(havingPropertyPath("passportTypeString").and(havingMessage("must be any of enum class com.baeldung.javaxval.enums.demo.PassportType")));
    assertThat(violations).anyMatch(havingPropertyPath("passportTypeOfSubset").and(havingMessage("must be any of [NEW, OLD]")));
    assertThat(violations).anyMatch(havingPropertyPath("passportTypeMatchesPattern").and(havingMessage("must match \"NEW|DEFAULT\"")));
  }

  public static Predicate<ConstraintViolation<Passport>> havingMessage(String message) {
    return l -> message.equals(l.getMessage());
  }

  public static Predicate<ConstraintViolation<Passport>> havingPropertyPath(String propertyPath) {
    return l -> propertyPath.equals(l.getPropertyPath()
        .toString());
  }
}
