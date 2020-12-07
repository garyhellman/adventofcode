package com.adventofcode.y2020;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = EyeColorValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EyeColorConstraint {
  Class<? extends Enum<?>> enumClass();
  String message() default "Invalid eye color, must be any of enum {enumClass}";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
