package com.adventofcode.y2020;

import ch.qos.logback.classic.Logger;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * --- Day 4: Passport Processing ---
 * You arrive at the airport only to realize that you grabbed your North Pole Credentials instead of your passport.
 * While these documents are extremely similar, North Pole Credentials aren't issued by a country and therefore
 * aren't actually valid documentation for travel in most of the world.
 * <p>
 * It seems like you're not the only one having problems, though; a very long line has formed for the automatic
 * passport scanners, and the delay could upset your travel itinerary.
 * <p>
 * Due to some questionable network security, you realize you might be able to solve both of these problems at the
 * same time.
 * <p>
 * The automatic passport scanners are slow because they're having trouble detecting which passports have all
 * required fields. The expected fields are as follows:
 * <p>
 * byr (Birth Year)
 * iyr (Issue Year)
 * eyr (Expiration Year)
 * hgt (Height)
 * hcl (Hair Color)
 * ecl (Eye Color)
 * pid (Passport ID)
 * cid (Country ID)
 * Passport data is validated in batch files (your puzzle input). Each passport is represented as a sequence of
 * key:value pairs separated by spaces or newlines. Passports are separated by blank lines.
 * <p>
 * Here is an example batch file containing four passports:
 * <p>
 * ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
 * byr:1937 iyr:2017 cid:147 hgt:183cm
 * <p>
 * iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
 * hcl:#cfa07d byr:1929
 * <p>
 * hcl:#ae17e1 iyr:2013
 * eyr:2024
 * ecl:brn pid:760753108 byr:1931
 * hgt:179cm
 * <p>
 * hcl:#cfa07d eyr:2025 pid:166559648
 * iyr:2011 ecl:brn hgt:59in
 * The first passport is valid - all eight fields are present. The second passport is invalid - it is missing
 * hgt (the Height field).
 * <p>
 * The third passport is interesting; the only missing field is cid, so it looks like data from North Pole Credentials,
 * not a passport at all! Surely, nobody would mind if you made the system temporarily ignore missing cid fields.
 * Treat this "passport" as valid.
 * <p>
 * The fourth passport is missing two fields, cid and byr. Missing cid is fine, but missing any other field is not,
 * so this passport is invalid.
 * <p>
 * According to the above rules, your improved system would report 2 valid passports.
 * <p>
 * Count the number of valid passports - those that have all required fields. Treat cid as optional.
 * In your batch file, how many passports are valid?
 */

//     public static void main(String[] args) {
//        SpringApplication.run(Y2020Application.class, args);
//    }
@SpringBootApplication
public class Y2020ApplicationDay04 implements CommandLineRunner {

  private static Logger LOG = (Logger) LoggerFactory
      .getLogger(Y2020ApplicationDay04.class);
  final String filename = "src/main/resources/passports.txt";
  final ArrayList<String> result = new ArrayList<>();


  public static void main(String[] args) {
    LOG.info("STARTING THE APPLICATION");
    SpringApplication.run(Y2020ApplicationDay03.class, args);
    LOG.info("APPLICATION FINISHED");
  }



  @Override
  public void run(String... args) {
    LOG.info("EXECUTING : command line runner");
    final ArrayList<String> result = new ArrayList<>();
    int numberPassports = 0;
    try {
      BufferedReader br = new BufferedReader(new FileReader(filename));
      while (br.ready()) {
        String line = br.readLine();
        if(line.isEmpty()) numberPassports++;
        result.add(line);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println(result.size());
//    assert (result.size() == 1000);

//    for (int i = 0; i < args.length; ++i) {
//      LOG.info("args[{}]: {}", i, args[i]);
//    }
    int out = extracted(result);
    // #1 guess - 207
    System.out.println(result.size());
    System.out.println("total number of passports: " + numberPassports);
    System.out.println("# of valid Passports: " + out);
// #2 guess 168 too high
    // 165 too low

  }

  public static int extracted(ArrayList<String> result) {
    boolean recordStarted = false;
    int validPassports = 0;
    int invalidPassports = 0;
    HashMap<String, String> holder = new HashMap();
    int ii = 1;

    recordStarted = true;

    for (String line : result) {
      System.out.println("" + ii++ + ":" + line.length() + " " + line);

      if (line.isEmpty()) {
//        System.out.println("blank******");
//        if (recordStarted) {
//          recordStarted = false;
//        }
//      }
//
//      if(!recordStarted) {
//        System.out.println("break ****");
        validPassports = getValidPassports(validPassports, holder);

//        for (Map.Entry<String, String> entry : holder.entrySet()) {
//          System.out.println(entry.getKey() + " : " + entry.getValue());
//        }
        System.out.println("---------------");
        holder.clear();
      } else {
        String[] keyVals = line.split(" ");
        int n = keyVals.length;
        String[] parts = null;
        for (String keyVal : keyVals) {
          if (n > 1) {
            parts = keyVal.split(":", n);
          } else {
            parts = keyVal.split(":");
          }
          holder.put(parts[0], parts[1]);
        }
      }

    }

    if (recordStarted) {
      System.out.println("last record not finished");
      validPassports = getValidPassports(validPassports, holder);
    }
    return validPassports;
  }

  public static int getValidPassports(int validPassports, HashMap<String, String> holder) {
    Passport passport = new PassportBuilder().setInMap(holder).createPassportWithMap();

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    Set<ConstraintViolation<Passport>> violations = validator.validate(passport);
    //By iterating over the violations, we can get all the violation messages using the getMessage method:

    System.out.println(passport.toString());
    if (violations.isEmpty()) {
      validPassports++;
      System.out.println("*** valid *** : " + validPassports);
    } else {
      System.out.println("*** INVALID *** ");
    }

    for (ConstraintViolation<Passport> violation : violations) {
      System.out.println(violation.getMessage());
    }
    return validPassports;
  }

}


