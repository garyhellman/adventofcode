package com.adventofcode.y2020;

import java.security.cert.CertPathBuilder;
import java.util.Map;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Passport {
  //    byr (Birth Year)
//    iyr (Issue Year)
//    eyr (Expiration Year)
//    hgt (Height)
//    hcl (Hair Color)
//    ecl (Eye Color)
//    pid (Passport ID)
//    cid (Country ID)
//    @NotNull(message = "Birth Year cannot be null")
//    String birthYear;
//
//    @NotNull(message = "Issue Year cannot be null")
//    String issueYear;
//
//    @NotNull(message = "Expiration Year cannot be null")
//    String expirationYear;
//
//    @NotNull(message = "Height cannot be null")
//    String height;
//
//    @NotNull(message = "Hair Color cannot be null")
//    String hairColor;
//
//    @NotNull(message = "Eye Color cannot be null")
//    String eyeColor;
//
//    @NotNull(message = "Passport ID cannot be null")
//    String passportID;
//
////    @NotNull(message = "Country ID cannot be null")
//    String countryID;
//
//    public String getBirthYear() {
//      return birthYear;
//    }
//    public Optional<@NotNull String> getBirthYear() {
//      return Optional.of(birthYear);
//    }

  @NotNull(message = "Birth Year cannot be null")
  @Min(value = 1920, message = "Birth Year should not be less than 1920")
  @Max(value = 2002, message = "Birth Year should not be greater than 2002")
  @Size(min = 4, max = 4, message = "Birth Year should be 4 chars long")
  String birthYear;

  @NotNull(message = "Issue Year cannot be null")
  @Min(value = 2010, message = "Issue Year should not be less than 2010")
  @Max(value = 2020, message = "Issue Year should not be greater than 2020")
  @Size(min = 4, max = 4, message = "Issue Year should be 4 chars long")
  String issueYear;

  @NotNull(message = "Expiration Year cannot be null")
  @Min(value = 2020, message = "Expiration Year should not be less than 2020")
  @Max(value = 2030, message = "Expiration Year should not be greater than 2030")
  @Size(min = 4, max = 4, message = "Expiration Year should be 4 chars long")
  String expirationYear;

  @NotNull(message = "Height cannot be null")
  @HeightConstraint
  String height;

  @NotNull(message = "Hair Color cannot be null")
  @HairColorConstraint
  String hairColor;

  @NotNull(message = "Eye Color cannot be null")
//  @ValueOfEnum(enumClass = EyeColorType.class)
    @EyeColorConstraint(enumClass = EyeColorType.class)
//  @EyeColorTypeSubset(anyOf = {EyeColorType.amb, EyeColorType.blu, EyeColorType.gry, EyeColorType.brn,
//      EyeColorType.hzl, EyeColorType.oth})
  String eyeColor;

  @NotNull(message = "Passport ID cannot be null")
//  @Size(min = 9, max = 9, message = "Passport ID should be 9 digits long")
  @PassportIdConstraint
  String passportID;

  //    @NotNull(message = "Country ID cannot be null")
  String countryID;

//  PassportType passportType;

  public Passport() {

  }

//  public PassportType getPassportType() {
//    return passportType;
//  }

//  public void setPassportType(PassportType passportType) {
//    this.passportType = passportType;
//  }

  public String getBirthYear() {
    return birthYear;
  }


  public void setBirthYear(String birthYear) {
    this.birthYear = birthYear;
  }

  public String getIssueYear() {
    return issueYear;
  }

  public void setIssueYear(String issueYear) {
    this.issueYear = issueYear;
  }

  public String getExpirationYear() {
    return expirationYear;
  }

  public void setExpirationYear(String expirationYear) {
    this.expirationYear = expirationYear;
  }

  public String getHeight() {
    return height;
  }

  public void setHeight(String height) {
    this.height = height;
  }

  public String getHairColor() {
    return hairColor;
  }

  public void setHairColor(String hairColor) {
    this.hairColor = hairColor;
  }

  public String getEyeColor() {
    return eyeColor;
  }

  public void setEyeColor(String eyeColor) {
    this.eyeColor = eyeColor;
  }

  public String getPassportID() {
    return passportID;
  }

  public void setPassportID(String passportID) {
    this.passportID = passportID;
  }

  public String getCountryID() {
    return countryID;
  }

  public void setCountryID(String countryID) {
    this.countryID = countryID;
  }

//  public EyeColorType getEyeColor() {
//    return eyeColor;
//  }
//
//  public void setEyeColor(EyeColorType eyeColor) {
//    this.eyeColor = eyeColor;
//  }

  public Passport(@NotNull(message = "Birth Year cannot be null") String birthYear,
                  @NotNull(message = "Issue Year cannot be null") String issueYear,
                  @NotNull(message = "Expiration Year cannot be null") String expirationYear,
                  @NotNull(message = "Height cannot be null") String height,
                  @NotNull(message = "Hair Color cannot be null") String hairColor,
                  @NotNull(message = "Eye Color cannot be null") String eyeColor,
                  @NotNull(message = "Passport ID cannot be null") String passportID,
                  @NotNull(message = "Country ID cannot be null") String countryID) {
    this.birthYear = birthYear;
    this.issueYear = issueYear;
    this.expirationYear = expirationYear;
    this.height = height;
    this.hairColor = hairColor;
//    this.eyeColor = EyeColorType.valueOfByName(eyeColor);
    this.eyeColor = eyeColor;
    this.passportID = passportID;
    this.countryID = countryID;
  }

//  public Passport(@NotNull(message = "Birth Year cannot be null") String birthYear,
//                  @NotNull(message = "Issue Year cannot be null") String issueYear,
//                  @NotNull(message = "Expiration Year cannot be null") String expirationYear,
//                  @NotNull(message = "Height cannot be null") String height,
//                  @NotNull(message = "Hair Color cannot be null") String hairColor,
//                  @NotNull(message = "Eye Color cannot be null") String eyeColor,
//                  @NotNull(message = "Passport ID cannot be null") String passportID,
//                  @NotNull(message = "Country ID cannot be null") String countryID) {
//  }

  public Passport(Map<String, String> inMap) {
    for (Map.Entry<String, String> entry : inMap.entrySet()) {
//        System.out.println(entry.getKey() + " : " + entry.getValue());
      String val = entry.getValue();
      switch (entry.getKey()) {
        case "byr":
          this.setBirthYear(val);
          break;
        case "iyr":
          this.setIssueYear(val);
          break;
        case "eyr":
          this.setExpirationYear(val);
          break;
        case "hgt":
          this.setHeight(val);
          break;
        case "hcl":
          this.setHairColor(val);
          break;
        case "ecl":
          this.setEyeColor(val);
//          this.setEyeColor(EyeColorType.valueOf(val));
//          this.setEyeColor(EyeColorType.valueOfByName(val));
          break;
        case "pid":
          this.setPassportID(val);
          break;
        case "cid":
          this.setCountryID(val);
          break;
//    byr (Birth Year)
//    iyr (Issue Year)
//    eyr (Expiration Year)
//    hgt (Height)
//    hcl (Hair Color)
//    ecl (Eye Color)
//    pid (Passport ID)
//    cid (Country ID)
      }
    }
//    System.out.println(this.toString());
  }

  @Override
  public String toString() {
    return "Passport{" +
        "birthYear='" + birthYear + '\'' +
        ", issueYear='" + issueYear + '\'' +
        ", expirationYear='" + expirationYear + '\'' +
        ", height='" + height + '\'' +
        ", hairColor='" + hairColor + '\'' +
        ", eyeColor='" + eyeColor + '\'' +
        ", passportID='" + passportID + '\'' +
        ", countryID='" + countryID + '\'' +
        '}';
  }

  public void setPassportTypeOfSubset(PassportType aNew) {
  }

  public void setPassportTypeString(String invalid) {
  }

  public void setPassportTypeMatchesPattern(PassportType old) {
  }


//  public static class Builder {
//    public CertPathBuilder withPassportTypeMatchesPattern(PassportType aDefault) {
//      return null;
//    }
//  }
}
