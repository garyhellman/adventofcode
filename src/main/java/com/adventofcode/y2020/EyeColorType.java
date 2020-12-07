package com.adventofcode.y2020;


public enum EyeColorType {
//  AMB("amb"), BLU("blu"), BRN("brn"), GRY("gry"), GRN("grn"), HZL("hzl"), OTH("oth");
  amb, blu, brn, gry, grn, hzl, oth;

  EyeColorType(String blu) {
  }

  EyeColorType() {
    //
  }

  @Override
  public String toString() {
    return "EyeColorType{}";
  }

  private String name;
  public String getName() {
    return this.name().toLowerCase();
  }

  public static EyeColorType valueOfByName(String name){
    return valueOf(name.toUpperCase());
  }

}

