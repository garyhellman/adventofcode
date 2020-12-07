package com.adventofcode.y2020;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import org.junit.Before;
//import org.junit.Test;

public class ValidationIntegrationTest {

  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  private Passport createPassport() {
    Passport passport = new PassportBuilder().createPassport();
//    Passport passport = new PassportBuilder().setBirthYear("2002").setIssueYear("2010").setExpirationYear("2022")
//   .setHeight("183cm").setHairColor("gry").setEyeColor("blu").setPassportID("123456").setCountryID("147").createPassport();

    passport.setBirthYear("2002");
    passport.setIssueYear("2010");
    passport.setHeight("183cm");
    passport.setEyeColor("blu");
    return passport;
  }

  @Test
  public void ifNameIsNull_nameValidationFails() {
    Passport passport = new PassportBuilder().createPassport();
    passport.setBirthYear("2002");
    passport.setIssueYear("2010");
    passport.setHeight("183cm");
    passport.setEyeColor("blu");
    Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
    assertEquals(violations.isEmpty(), false);
  }

  @Test
  public void ifSizeNotInRange_aboutMeValidationFails() {
    Passport passport = new PassportBuilder().createPassport();
    passport.setBirthYear("1002");
    passport.setIssueYear("2010");
    passport.setHeight("183cm");
    passport.setEyeColor("blu");

    Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
    assertEquals(violations.isEmpty(), false);
  }

  @Test
  public void ifWorkingIsFalse_workingValidationFails() {
    Passport passport = new PassportBuilder().createPassport();
    passport.setBirthYear("2002");
    passport.setIssueYear("2010");
    passport.setHeight("183cm");
    passport.setEyeColor("blu");

    Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
    assertEquals(violations.isEmpty(), false);
  }

  @Test
  public void ifAgeNotRange_ageValidationFails() {
    Passport passport = new PassportBuilder().createPassport();
    passport.setBirthYear("2002");
    passport.setIssueYear("2010");
    passport.setHeight("183cm");
    passport.setEyeColor("blu");

    Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
    assertEquals(violations.isEmpty(), false);
  }

  @Test
  public void ifFnameNullAgeNotRangeAndWorkingIsFalse_validationFailsWithThreeErrors() {
    Passport passport = new PassportBuilder().createPassport();
    passport.setBirthYear("2002");
    passport.setIssueYear("2010");
    passport.setHeight("183cm");
    passport.setEyeColor("blu");

    Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
    assertEquals(violations.isEmpty(), false);
    assertEquals(violations.size(), 3);
  }

  @Test
  public void givenInvalidEmail_thenValidationFails() {
    Passport passport = createPassport();
    passport.setBirthYear("2002");
    passport.setIssueYear("2010");
    passport.setHeight("183cm");
    passport.setEyeColor("blu");

    Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
    assertEquals(1, violations.size());
  }

//  @Test
//  public void givenBlankPreference_thenValidationFails() {
//    Passport passport = createPassport();
//    passport.setPreferences(Collections.singletonList(" "));
//
//    Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
//    assertEquals(1, violations.size());
//  }
//
//  @Test
//  public void givenEmptyOptional_thenValidationSucceeds() {
//    Passport passport = createPassport();
//
//    Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
//    assertEquals(0, violations.size());
//  }
//
//  @Test
//  public void givenPastDateOfBirth_thenValidationSuccess() {
//    Passport passport = createPassport();
//    passport.setDateOfBirth(LocalDate.of(1980, 5, 20));
//
//    Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
//    assertEquals(0, violations.size());
//
//  }
}
