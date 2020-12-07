package com.adventofcode.y2020;


import static org.assertj.core.api.Assertions.assertThat;

import com.adventofcode.y2020.demo.PassportUnitTest;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import com.baeldung.javaxval.enums.demo.PassportUnitTest;
//import com.baeldung.javaxval.enums.demo.Passport;
//import org.junit.BeforeClass;
//import org.junit.Test;

public class ValueOfEnumValidatorUnitTest {

  private static Validator validator;

  @BeforeEach
  public void setupValidatorInstance() {
    validator = Validation.buildDefaultValidatorFactory()
        .getValidator();
  }

  @Test
  public void whenStringAnyOfEnum_thenShouldNotReportConstraintViolations() {
    Passport passport = new PassportBuilder().createPassport();
//    passport.setBirthYear("2002");
//    passport.setPassportTypeString("DEFAULT");
//    passport.setEyeColor(EyeColorType.valueOf("blu".toUpperCase(Locale.ROOT)));
    passport.setBirthYear("2002");
    passport.setIssueYear("2010");
    passport.setExpirationYear("2022");
    passport.setHeight("183cm");
    passport.setHairColor("gry");
//    passport.setEyeColor(EyeColorType.OTH);
//    passport.setEyeColor(EyeColorType.BLU);
    passport.setPassportID("123456");
    passport.setCountryID("147");

    passport.setEyeColor("blu");
//    Passport passport = new PassportBuilder().withPassportTypeString("DEFAULT").build();
    Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
    assertThat(violations.isEmpty()).isTrue();
  }

  @Test
  public void whenStringNull_thenShouldNotReportConstraintViolations() {
    Passport passport = new PassportBuilder().createPassport();
    passport.setPassportTypeString(null);
//    Passport passport = new PassportBuilder().withPassportTypeString(null)
//        .build();
    passport.setBirthYear("2002");
    passport.setIssueYear("2010");
    passport.setExpirationYear("2022");
    passport.setHeight("183cm");
    passport.setHairColor("gry");
//    passport.setEyeColor(EyeColorType.OTH);
//    passport.setEyeColor(EyeColorType.BLU);
    passport.setPassportID("123456");
    passport.setCountryID("147");

    passport.setEyeColor("blu");
//    passport.setEyeColor(null);


    Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
    assertThat(violations.isEmpty()).isTrue();
  }

  @Test
  public void whenStringNotAnyOfEnum_thenShouldGiveOccurrenceOfConstraintViolations() {
//    Passport passport = new PassportBuilder().withPassportTypeString("test")
//        .build();
    Passport passport = new PassportBuilder().createPassport();
    passport.setBirthYear("3003");
//    passport.setEyeColor(EyeColorType.valueOf("PINK".toUpperCase(Locale.ROOT)));
//    passport.setEyeColor(EyeColorType.valueOf("xxx".toUpperCase(Locale.ROOT)));
//    passport.setPassportTypeString("test");
    Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
    assertThat(violations.size()).isEqualTo(7);

    assertThat(violations).anyMatch(PassportUnitTest.havingPropertyPath("birthYear")
        .and(PassportUnitTest.havingMessage("Birth Year should not be greater than 2020")));
//    assertThat(violations).anyMatch(PassportUnitTest.havingPropertyPath("passportTypeString")
//        .and(PassportUnitTest.havingMessage("must be any of enum class com.baeldung.javaxval.enums.demo.PassportType")));
  }
}
