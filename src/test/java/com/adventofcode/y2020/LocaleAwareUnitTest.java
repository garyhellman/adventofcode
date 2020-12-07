package com.adventofcode.y2020;

//package com.baeldung.javaxval;

import java.util.Locale;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

//import org.junit.AfterClass;
//import org.junit.BeforeClass;

public abstract class LocaleAwareUnitTest {
  private static Locale previousDefault;

  @BeforeAll
  public static void setupLocale() {
    previousDefault = Locale.getDefault();

    Locale.setDefault(Locale.US);
  }

  @AfterAll
  public static void resetLocale() {
    Locale.setDefault(previousDefault);
  }

}
