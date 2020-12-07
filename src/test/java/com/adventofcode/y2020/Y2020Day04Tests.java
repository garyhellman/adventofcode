package com.adventofcode.y2020;

import ch.qos.logback.classic.Logger;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Predicate;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.LoggerFactory;

import static com.adventofcode.y2020.Y2020ApplicationDay04.extracted;
import static com.adventofcode.y2020.demo.PassportUnitTest.havingMessage;
import static com.adventofcode.y2020.demo.PassportUnitTest.havingPropertyPath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * --- Part Two ---
 * The line is moving more quickly now, but you overhear airport security talking about how passports with
 * invalid data are getting through. Better add some data validation, quick!
 * <p>
 * You can continue to ignore the cid field, but each other field has strict rules about what values are valid
 * for automatic validation:
 * <p>
 * byr (Birth Year) - four digits; at least 1920 and at most 2002.
 * iyr (Issue Year) - four digits; at least 2010 and at most 2020.
 * eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
 * hgt (Height) - a number followed by either cm or in:
 * If cm, the number must be at least 150 and at most 193.
 * If in, the number must be at least 59 and at most 76.
 * hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
 * ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
 * pid (Passport ID) - a nine-digit number, including leading zeroes.
 * cid (Country ID) - ignored, missing or not.
 * Your job is to count the passports where all required fields are both present and valid according to the above rules. Here are some example values:
 * <p>
 * byr valid:   2002
 * byr invalid: 2003
 * <p>
 * hgt valid:   60in
 * hgt valid:   190cm
 * hgt invalid: 190in
 * hgt invalid: 190
 * <p>
 * hcl valid:   #123abc
 * hcl invalid: #123abz
 * hcl invalid: 123abc
 * <p>
 * ecl valid:   brn
 * ecl invalid: wat
 * <p>
 * pid valid:   000000001
 * pid invalid: 0123456789
 * Here are some invalid passports:
 * <p>
 * eyr:1972 cid:100
 * hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926
 * <p>
 * iyr:2019
 * hcl:#602927 eyr:1967 hgt:170cm
 * ecl:grn pid:012533040 byr:1946
 * <p>
 * hcl:dab227 iyr:2012
 * ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277
 * <p>
 * hgt:59cm ecl:zzz
 * eyr:2038 hcl:74454a iyr:2023
 * pid:3556412378 byr:2007
 * Here are some valid passports:
 * <p>
 * pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
 * hcl:#623a2f
 * <p>
 * eyr:2029 ecl:blu cid:129 byr:1989
 * iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm
 * <p>
 * hcl:#888785
 * hgt:164cm byr:2001 iyr:2015 cid:88
 * pid:545766238 ecl:hzl
 * eyr:2022
 * <p>
 * iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719
 * Count the number of valid passports - those that have all required fields and valid values.
 * Continue to treat cid as optional. In your batch file, how many passports are valid?
 */


public class Y2020Day04Tests {

  final String filename = "src/test/resources/passports.txt";
  private static Logger LOG = (Logger) LoggerFactory
      .getLogger(Y2020Day04Tests.class);

  ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  Validator validator = factory.getValidator();

//  static { //runs when the main class is loaded.
//    System.setProperty("org.jboss.logging.provider", "slf4j");
//  }

  @Disabled
  @Test
  void readFileintoArray() {


    final ArrayList<String> result = new ArrayList<>();
    try {
      BufferedReader br = new BufferedReader(new FileReader(filename));
      while (br.ready()) {
        String line = br.readLine();
        StringBuffer sb = new StringBuffer(320);
        int y = 3 * 325 / line.length();
        for (int i = 0; i <= y; i++) {
          sb.append(line);
        }
//                result.add(br.readLine());
        result.add(sb.toString());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
//        assert (result.size() == 323);
//        assert (result.get(0).length() == 31);
    int ii = 1;
    for (String line : result) {
      System.out.println("" + ii++ + ":" + line.length() + " " + line);
    }


  }

  @Disabled
  @Test
  void readAllLinesFileintoArray() {
    List<Integer> numbers = new ArrayList<>();
    Charset charset = Charset.forName("ISO-8859-1");
    try {
      for (String line : Files.readAllLines(Paths.get(filename), charset)) {
        for (String part : line.split("\\s+")) {
          Integer i = Integer.valueOf(part);
          numbers.add(i);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    assert (numbers.size() == 200);
  }

  @Disabled
  @Test
  public void givenFilePath_whenUsingFilesLines_thenFileData() {
//        String expectedData = "Hello, world!";

    Path path = null;
    try {
      path = Paths.get(getClass().getClassLoader()
          .getResource("input.txt").toURI());
      Stream<String> lines = null;
      lines = Files.lines(path);
      String data = lines.collect(Collectors.joining("\n"));
      lines.close();

      assert (data.trim().contains("1602"));

//            System.out.println(data.trim());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Disabled
  @Test
  public void givenFilePath_streamFileData() {
    try {
      List<Integer> numbers = Files.lines(Paths.get(filename))
          .map(line -> line.split("\\s+")).flatMap(Arrays::stream)
          .map(Integer::valueOf)
          .collect(Collectors.toList());
      assert (numbers.size() == 200);
      assert (numbers.get(numbers.size() - 1) == 1602);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void whenAllAcceptable_thenShouldNotGiveConstraintViolations() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    Passport passport = new PassportBuilder().setBirthYear("2002").setIssueYear("2010").setExpirationYear("2022")
        .setHeight("183cm").setHairColor("#123abc")
        .setEyeColor("blu").setPassportID("123456789").setCountryID("147").createPassport();
    //ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n",
    //            "byr:1937 iyr:2017 cid:147 hgt:183cm
//    passport.setEyeColor(new EyeColorType(EyeColorType.AMB));
    Set violations = validator.validate(passport);
    assertThat(violations).isEmpty();
  }

  @Test
  public void whenAllNull_thenOnlyNotNullShouldGiveConstraintViolations() {

    Passport passport = new PassportBuilder().createPassport();
//    "2002", "2010", "2022", "183cm",
//        "gry", "blu", "123456", "147");

    Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
    assertThat(violations.size()).isEqualTo(11);

    assertThat(violations)
        .anyMatch(havingPropertyPath("passportID")
            .and(havingMessage("Invalid Passport ID")));
    assertThat(violations)
        .anyMatch(havingPropertyPath("issueYear")
            .and(havingMessage("Issue Year cannot be null")));
    assertThat(violations)
        .anyMatch(havingPropertyPath("birthYear")
            .and(havingMessage("Birth Year cannot be null")));
    assertThat(violations)
        .anyMatch(havingPropertyPath("height")
            .and(havingMessage("Height cannot be null")));
    assertThat(violations)
        .anyMatch(havingPropertyPath("hairColor")
            .and(havingMessage("Hair Color cannot be null")));
    assertThat(violations)
        .anyMatch(havingPropertyPath("expirationYear")
            .and(havingMessage("Expiration Year cannot be null")));
    assertThat(violations)
        .anyMatch(havingPropertyPath("passportID")
            .and(havingMessage("Passport ID cannot be null")));
    assertThat(violations)
        .anyMatch(havingPropertyPath("eyeColor")
            .and(havingMessage("Eye Color cannot be null")));
  }

  @Test
  public void whenAllInvalid_thenViolationsShouldBeReported() {
    Passport passport = new Passport();
    passport.setPassportTypeString("invalid");
    passport.setPassportTypeOfSubset(PassportType.DEFAULT);
    passport.setPassportTypeMatchesPattern(PassportType.OLD);

    Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
    assertThat(violations.size()).isEqualTo(11);

    assertThat(violations)
        .anyMatch(havingPropertyPath("issueYear")
            .and(havingMessage("Issue Year cannot be null")));
    assertThat(violations)
        .anyMatch(havingPropertyPath("birthYear")
            .and(havingMessage("Birth Year cannot be null")));
    assertThat(violations)
        .anyMatch(havingPropertyPath("height")
            .and(havingMessage("Height cannot be null")));
    assertThat(violations)
        .anyMatch(havingPropertyPath("hairColor")
            .and(havingMessage("Hair Color cannot be null")));
    assertThat(violations)
        .anyMatch(havingPropertyPath("expirationYear")
            .and(havingMessage("Expiration Year cannot be null")));
    assertThat(violations)
        .anyMatch(havingPropertyPath("passportID")
            .and(havingMessage("Passport ID cannot be null")));
    assertThat(violations)
        .anyMatch(havingPropertyPath("eyeColor")
            .and(havingMessage("Invalid eye color, must be any of enum class com.adventofcode.y2020.EyeColorType")));

//        assertThat(violations)
//        .anyMatch(havingPropertyPath("passportTypeString")
//            .and(havingMessage("must be any of enum class com.baeldung.javaxval.enums.demo.PassportType")));
//    assertThat(violations)
//        .anyMatch(havingPropertyPath("passportTypeOfSubset")
//            .and(havingMessage("must be any of [NEW, OLD]")));
//    assertThat(violations)
//        .anyMatch(havingPropertyPath("passportTypeMatchesPattern")
//            .and(havingMessage("must match \"NEW|DEFAULT\"")));
  }

  @Test
  void testTheirValidPassports() {
    String[] lines =
        {"pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980\n",
            "hcl:#623a2f\n",
            "\n",

            "eyr:2029 ecl:blu cid:129 byr:1989\n",
            "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm\n",
            "\n",

            "hcl:#888785\n",
            "hgt:164cm byr:2001 iyr:2015 cid:88\n",
            "pid:545766238 ecl:hzl\n",
            "eyr:2022\n",
            "\n",

            "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719\n",
        };


    final ArrayList<String> result = new ArrayList<>();
    for (int i = 0; i < lines.length; i++) {
      String line = lines[i].trim();
      result.add(line);
      System.out.println(line);
    }


    int validPassports = extracted(result);
    // #1 guess - 207
    System.out.println("# of valid Passports: " + validPassports);

    assertEquals(4, validPassports);
    System.out.println("validPaasspoerts:" + validPassports);
  }

  @Test
  void testTheirBadPassports() {
    String[] lines =
        {"eyr:1972 cid:100\n",
    "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926\n",
            "\n",

    "iyr:2019\n",
    "hcl:#602927 eyr:1967 hgt:170cm\n",
    "ecl:grn pid:012533040 byr:1946\n",
            "\n",

    "hcl:dab227 iyr:2012\n",
    "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277\n",
            "\n",

    "hgt:59cm ecl:zzz\n",
    "eyr:2038 hcl:74454a iyr:2023\n",
    "pid:3556412378 byr:2007\n",
        };


    final ArrayList<String> result = new ArrayList<>();
    for (int i = 0; i < lines.length; i++) {
      String line = lines[i].trim();
      result.add(line);
//      System.out.println(line);
    }


    int validPassports = extracted(result);
    // #1 guess - 207
    System.out.println("# of valid Passports: " + validPassports);

    assertEquals(0, validPassports);
    System.out.println(validPassports);
  }

  @Test
  void testTheirData() {


//    String[] lines =
//        {"ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n",
//            "byr:1937 iyr:2017 cid:147 hgt:183cm\n",
//            "\n",
//            "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n",
//            "hcl:#cfa07d byr:1929\n",
//            "\n",
//            "hcl:#ae17e1 iyr:2013\n",
//            "eyr:2024\n",
//            "ecl:brn pid:760753108 byr:1931\n",
//            "hgt:179cm\n",
//            "\n",
//            "hcl:#cfa07d eyr:2025 pid:166559648\n",
//            "iyr:2011 ecl:brn hgt:59in"
//        };
//
    String[] lines =
        {"ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n",
            "byr:1937 iyr:2017 cid:147 hgt:183cm\n",
            "\n",
            "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n",
            "byr:1937 iyr:2011 cid:147 hgt:190cm\n",
            "\n",
            "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n",
            "byr:1937 iyr:2011 cid:147 hgt:183cm\n",
            "\n",
            "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n",
            "hcl:#cfa07d byr:1929\n",
            "\n",
            "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n",
            "hcl:#cfa07d byr:1929\n",
            "hgt:190cm\n",
            "\n",
            "hcl:#ae17e1 iyr:2013\n",
            "eyr:2024\n",
            "ecl:brn pid:760753108 byr:1931\n",
            "hgt:179cm\n",
            "\n",
            "hcl:#cfa07d eyr:2025 pid:166559648\n",
            "iyr:2011 ecl:brn hgt:59in"
        };


    final ArrayList<String> result = new ArrayList<>();
    for (int i = 0; i < lines.length; i++) {
      String line = lines[i].trim();
      result.add(line);
//      System.out.println(line);
    }

////    Map<String, String> result = Splitter.on(',')
////        .trimResults()
////        .withKeyValueSeparator(
////            Splitter.on('=')
////                .limit(2)
////                .trimResults())
////        .split(input);
//
//    int validPassports = 0;
//    HashMap<String, String> holder = new HashMap();
//    int ii = 1;
//    for (String line : result) {
//      System.out.println("" + ii++ + ":" + line.length() + " " + line);
//      if (line.isEmpty()) {
////        System.out.println("break ****");
//        Passport passport = new PassportBuilder().setInMap(holder).createPassportWithMap();
//
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//
//        Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
//        //By iterating over the violations, we can get all the violation messages using the getMessage method:
//
//        if (violations.isEmpty()) {
//          validPassports++;
//          System.out.println("*** valid ***");
//        } else {
//          System.out.println("*** INVALID ***");
//        }
//
//        for (ConstraintViolation<Passport> violation : violations) {
//          System.out.println(violation.getMessage());
//        }
//
////        for (Map.Entry<String, String> entry : holder.entrySet()) {
////          System.out.println(entry.getKey() + " : " + entry.getValue());
////        }
//        System.out.println();
//        holder.clear();
//      } else {
//        String[] keyVals = line.split(" ");
//        int n = keyVals.length;
////        System.out.println(n);
//        String[] parts = null;
//        for (String keyVal : keyVals) {
//          if (n > 1) {
//            parts = keyVal.split(":", n);
//          } else {
//            parts = keyVal.split(":");
//          }
////          System.out.println(parts[0]);
//          holder.put(parts[0], parts[1]);
//        }
//      }
//    }

    int validPassports = extracted(result);
    // #1 guess - 207
    System.out.println("# of valid Passports: " + validPassports);

    assertEquals(5, validPassports);
    System.out.println(validPassports);
  }


}

