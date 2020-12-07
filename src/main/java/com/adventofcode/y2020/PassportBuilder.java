package com.adventofcode.y2020;

import java.security.cert.CertPathBuilder;
import java.util.Map;
import javax.validation.constraints.NotNull;

public class PassportBuilder {
  private @NotNull(message = "Birth Year cannot be null") String birthYear;
  private @NotNull(message = "Issue Year cannot be null") String issueYear;
  private @NotNull(message = "Expiration Year cannot be null") String expirationYear;
  private @NotNull(message = "Height cannot be null") String height;
  private @NotNull(message = "Hair Color cannot be null") String hairColor;
  private @NotNull(message = "Eye Color cannot be null") String eyeColor;
  private @NotNull(message = "Passport ID cannot be null") String passportID;
  private @NotNull(message = "Country ID cannot be null") String countryID;
  private @NotNull(message = "Passport type cannot be null") PassportType passportType;
  private Map<String, String> inMap;

  public PassportBuilder setBirthYear(@NotNull(message = "Birth Year cannot be null") String birthYear) {
    this.birthYear = birthYear;
    return this;
  }

  public PassportBuilder setIssueYear(@NotNull(message = "Issue Year cannot be null") String issueYear) {
    this.issueYear = issueYear;
    return this;
  }

  public PassportBuilder setExpirationYear(@NotNull(message = "Expiration Year cannot be null") String expirationYear) {
    this.expirationYear = expirationYear;
    return this;
  }

  public PassportBuilder setHeight(@NotNull(message = "Height cannot be null") String height) {
    this.height = height;
    return this;
  }

  public PassportBuilder setHairColor(@NotNull(message = "Hair Color cannot be null") String hairColor) {
    this.hairColor = hairColor;
    return this;
  }

  public PassportBuilder setEyeColor(@NotNull(message = "Eye Color cannot be null") String eyeColor) {
    this.eyeColor = eyeColor;
    return this;
  }

  public PassportBuilder setPassportID(@NotNull(message = "Passport ID cannot be null") String passportID) {
    this.passportID = passportID;
    return this;
  }

  public PassportBuilder setCountryID(@NotNull(message = "Country ID cannot be null") String countryID) {
    this.countryID = countryID;
    return this;
  }

  public PassportBuilder setInMap(Map<String, String> inMap) {
    this.inMap = inMap;
    return this;
  }

  public Passport createPassport() {
    return new Passport(birthYear, issueYear, expirationYear, height, hairColor, eyeColor, passportID, countryID);
  }

//  public PassportBuilder withEyeColor(EyeColorType ecl) {
//    this.eyeColor = ecl;
//    return this;
//  }
//
//  public PassportBuilder withPassportTypeString(String aDefault) {
//    this.passportType = PassportType.valueOf(aDefault);
//    return this;
//  }

  public Passport createPassportWithMap() {
    return new Passport(this.inMap);
  }
}
