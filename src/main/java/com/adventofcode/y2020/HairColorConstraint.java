package com.adventofcode.y2020;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

//  Hair Color) - a # followed by exactly six characters 0-9 or a-f.

@Documented
@Constraint(validatedBy = HairColorValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface HairColorConstraint {
  String message() default "Invalid Hair Color";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
