package com.adventofcode.y2020;

import static com.adventofcode.y2020.PassportType.OLD;
import static org.assertj.core.api.Assertions.assertThat;
//import static com.baeldung.javaxval.enums.demo.PassportType.DEFAULT;
//import static com.baeldung.javaxval.enums.demo.PassportType.OLD;

import com.adventofcode.y2020.demo.PassportUnitTest;
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

public class EnumNamePatternValidatorUnitTest {

  private static Validator validator;

  @BeforeEach
  public void setupValidatorInstance() {
    validator = Validation.buildDefaultValidatorFactory()
        .getValidator();
  }

//  @Test
//  public void whenEnumMatchesRegex_thenShouldNotReportConstraintViolations() {
////    Passport passport = new PassportBuilder().withPassportTypeMatchesPattern(PassportType.DEFAULT)
////        .build();
//    Passport passport = new PassportBuilder().createPassport();
//    passport.setPassportType(PassportType.DEFAULT);
//    Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
//    assertThat(violations.isEmpty()).isTrue();
//  }

  @Test
  public void whenEnumNull_thenShouldNotReportConstraintViolations() {
    Passport passport = new PassportBuilder().createPassport();
    passport.setPassportTypeString(null);
//    Passport passport = new PassportBuilder().withPassportTypeMatchesPattern(null)
//        .build();
    Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
    assertThat(violations.isEmpty()).isTrue();
  }

  @Test
  public void whenEnumDoesNotMatchRegex_thenShouldGiveOccurrenceOfConstraintViolations() {
    Passport passport = new PassportBuilder().createPassport();
    passport.setPassportTypeString("invalid");
    passport.setPassportTypeOfSubset(PassportType.DEFAULT);
    passport.setPassportTypeMatchesPattern(PassportType.OLD);


//    Passport passport = new PassportBuilder().withPassportTypeMatchesPattern(OLD)
//        .build();
    Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
    assertThat(violations.size()).isEqualTo(7);

    assertThat(violations).anyMatch(PassportUnitTest.havingPropertyPath("passportTypeMatchesPattern")
        .and(PassportUnitTest.havingMessage("must match \"NEW|DEFAULT\"")));
  }
}
