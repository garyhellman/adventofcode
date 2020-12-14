package com.adventofcode.y2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.adventofcode.y2020.Y2020ApplicationDay04.extracted;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Test {

  private Day06 day06UnderTest;
//  String pattern = "FBFBBFFRLR";

  @BeforeEach
  void setUp() {
    day06UnderTest = new Day06();
  }

  @Test
  void testRun() {
    // Setup

    // Run the test
    day06UnderTest.run("args");

    // Verify the results
  }

  @Test
  void testGetAll() {
    // Setup

    // Run the test
    ArrayList<String> result = day06UnderTest.makeTotalList();

    // Verify the results
    assertThat(result.size()).isEqualTo(44);
  }


  @Test
  void testNumberofGroups() {
    final String filename = "src/main/resources/puzzle.txt";
    final ArrayList<String> result = new ArrayList<>();

    try {
      BufferedReader br = new BufferedReader(new FileReader(filename));
      while (br.ready()) {
        String line = br.readLine();
        result.add(line);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    assertThat(result.size()).isEqualTo(2148);
//    System.out.println(result.size());

  }

  @Test
  void testNumberofGroupsAlso() {
    final String filename = "src/main/resources/puzzle.txt";
    final ArrayList<String> result = new ArrayList<>();
    final ArrayList<String> group = new ArrayList<>();

    boolean recordStarted = false;
    recordStarted = true;
    int totalGroups = 0;
    int groups = 0;

    try {
      BufferedReader br = new BufferedReader(new FileReader(filename));
      while (br.ready()) {
        String line = br.readLine();
        recordStarted = true;
        if (line.isEmpty()) {
//          System.out.println("new group");
          groups++;
          int nn = processGroups(group);
          totalGroups += nn;
          group.clear();
          recordStarted = false;
        }
        group.add(line);
        result.add(line);
      }
      if (recordStarted) {
        System.out.println("last record not finished");
        groups++;
        int nn = processGroups(group);
        totalGroups += nn;
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    assertThat(groups).isEqualTo(487);
    System.out.println(result.size());
    System.out.println("groups: " + groups);
    System.out.println("totalGroups: " + totalGroups);

  }

  @Test
  void testNumberofGroupsAlsoPart2() {
    final String filename = "src/main/resources/puzzle.txt";
    final ArrayList<String> result = new ArrayList<>();
    final ArrayList<String> group = new ArrayList<>();

    boolean recordStarted = false;
    int totalGroups = 0;
    int groups = 0;


    try {
      BufferedReader br = new BufferedReader(new FileReader(filename));
      while (br.ready()) {
        String line = br.readLine();
        result.add(line);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    int ii = 1;
    recordStarted = true;

    for (String line2 : result) {
      System.out.println("" + ii++ + ":" + line2.length() + " " + line2.trim());
      line2 = line2.trim();

      recordStarted = true;
      if (line2.isBlank()) {
        System.out.println("new group");
        groups++;
        int nn = processGroupsPart2(group);
        System.out.println("answers in that group: " + nn);
        totalGroups += nn;
        group.clear();
        recordStarted = false;
      } else {
        group.add(line2);
      }

    }

    if (recordStarted) {
      System.out.println("last record not finished");
      groups++;
      int nn = processGroupsPart2(group);
      totalGroups += nn;
    }

    System.out.println("totalGroups: " + totalGroups);
    assertThat(totalGroups).isEqualTo(3427);

  }

  private int processGroups(ArrayList<String> theGroup) {
//    System.out.println("processGroups:");
    int numQuestions = 0;
    SortedSet<String> groupSet = new TreeSet<>();

    Iterator<String> itr = theGroup.iterator();
    while (itr.hasNext()) {
      String s = itr.next();
      String[] letters = s.split("");
      for (String ss : letters) {
        if (!ss.isBlank()) {
          System.out.println(" the letter: " + ss);
          groupSet.add(ss);
        }
      }
//      System.out.println(s);
    }

    Iterator<String> itr2 = groupSet.iterator();
    while (itr2.hasNext()) {
      String sss = itr2.next();
//      System.out.println(sss);
      numQuestions++;
    }

    return numQuestions;
    // 7018 is too high
  }

  private int processGroupsPart2(ArrayList<String> theGroup) {
    int groupSize = theGroup.size();
    System.out.println("processGroups: people in group: " + groupSize);
    int numQuestions = 0;
    SortedSet<String> groupSet = new TreeSet<>();
    HashMap<Character, Integer> letterHashMap = new HashMap<>(32);

    Iterator<String> itr = theGroup.iterator();
    while (itr.hasNext()) {
      String s = itr.next();
//      String[] letters = s.split("");
      char[] chars = s.toCharArray();
      for (Character ch : chars) {
        System.out.println(" the char: " + ch);
        if (!letterHashMap.containsKey(ch)) {
          letterHashMap.put(ch, 1);
//          groupSet.add(ss);
        } else {
          Integer i = letterHashMap.get(ch);
          letterHashMap.replace(ch, ++i);
          System.out.println("This is a more than one");
//          numQuestions++;
        }
      }
//      System.out.println(s);
    }


    letterHashMap.forEach((key, value) -> System.out.println("[Key] : " + key + " [Value] : " + value));
    for (Map.Entry<Character, Integer> entry : letterHashMap.entrySet()) {
      System.out.println(entry.getKey() + " " + entry.getValue());
      if (entry.getValue() == groupSize) {
        numQuestions++;
      }
    }

    return numQuestions;
    // 7018 is too high
  }

  @Test
  void testTheirData() {
    String[] lines =
        {"abc\n",
            "\n",
            "a\n",
            "b\n",
            "c\n",
            "\n",
            "ab\n",
            "ac\n",
            "\n",
            "a\n",
            "a\n",
            "a\n",
            "a\n",
            "\n",
            "b"
        };

    int totalGroups = 0;
    int groups = 0;
    final ArrayList<String> result = new ArrayList<>();
    final ArrayList<String> group = new ArrayList<>();

    for (int i = 0; i < lines.length; i++) {
      String line = lines[i];
//      System.out.println("" + i + ":" + line.trim().length() + " " + line.trim());
      result.add(line);
//      System.out.println(line);
    }

    boolean recordStarted = false;
    int ii = 1;

    recordStarted = true;

    for (String line2 : result) {
      System.out.println("" + ii++ + ":" + line2.length() + " " + line2.trim());
      line2 = line2.trim();

      recordStarted = true;
      if (line2.isBlank()) {
        System.out.println("new group");
        groups++;
        int nn = processGroups(group);
        System.out.println("answers in that group: " + nn);
        totalGroups += nn;
        group.clear();
        recordStarted = false;
      }

      group.add(line2);
    }

    if (recordStarted) {
      System.out.println("last record not finished");
      groups++;
      int nn = processGroups(group);
      totalGroups += nn;
    }

    assertThat(totalGroups).isEqualTo(11);
  }

  @Test
  void testTheirDataPart2() {
    String[] lines =
        {"abc\n",
            "\n",
            "a\n",
            "b\n",
            "c\n",
            "\n",
            "ab\n",
            "ac\n",
            "\n",
            "a\n",
            "a\n",
            "a\n",
            "a\n",
            "\n",
            "b"
        };

    int totalGroups = 0;
    int groups = 0;
    final ArrayList<String> result = new ArrayList<>();
    final ArrayList<String> group = new ArrayList<>();

    for (int i = 0; i < lines.length; i++) {
      String line = lines[i];
//      System.out.println("" + i + ":" + line.trim().length() + " " + line.trim());
//      if (!line.isBlank()) {
      result.add(line);
//      }
//      System.out.println(line);
    }

    boolean recordStarted = false;
    int ii = 1;

    recordStarted = true;

    for (String line2 : result) {
      System.out.println("" + ii++ + ":" + line2.length() + " " + line2.trim());
      line2 = line2.trim();

      recordStarted = true;
      if (line2.isBlank()) {
        System.out.println("new group");
        groups++;
        int nn = processGroupsPart2(group);
        System.out.println("answers in that group: " + nn);
        totalGroups += nn;
        group.clear();
        recordStarted = false;
      } else {
        group.add(line2);
      }

    }

    if (recordStarted) {
      System.out.println("last record not finished");
      groups++;
      int nn = processGroupsPart2(group);
      totalGroups += nn;
    }

    assertThat(totalGroups).isEqualTo(6);
  }


//  @Test
//  void testGetRow() {
//    // Setup
//
//    // Run the test
//    int result = day06UnderTest.getRow(pattern);
//
//    // Verify the results
//    assertThat(result).isEqualTo(44);
//
//    result = day06UnderTest.getRow("BFFFBBFRRR");
//    assertThat(result).isEqualTo(70);
//    result = day06UnderTest.getRow("FFFBBBFRRR");
//    assertThat(result).isEqualTo(14);
//    result = day06UnderTest.getRow("BBFFBBFRLL");
//    assertThat(result).isEqualTo(102);
//
//
////     * BFFFBBFRRR: row 70, column 7, seat ID 567.
////        * FFFBBBFRRR: row 14, column 7, seat ID 119.
////        * BBFFBBFRLL: row 102, column 4, seat ID 820.
//  }
//
//  @Test
//  void testGetCol() {
//    // Setup
//
//    // Run the test
//    int result = day06UnderTest.getCol(pattern);
//
//    // Verify the results
//    assertThat(result).isEqualTo(5);
//    result = day06UnderTest.getCol("BFFFBBFRRR");
//    assertThat(result).isEqualTo(7);
//    result = day06UnderTest.getCol("FFFBBBFRRR");
//    assertThat(result).isEqualTo(7);
//    result = day06UnderTest.getCol("BBFFBBFRLL");
//    assertThat(result).isEqualTo(4);
//
//
////     * BFFFBBFRRR: row 70, column 7, seat ID 567.
////        * FFFBBBFRRR: row 14, column 7, seat ID 119.
////        * BBFFBBFRLL: row 102, column 4, seat ID 820.
//  }
//
//  @Test
//  void testGetPatternPlusOne() {
//    String pat = "FFFFFFFLLL";
//    String result = day06UnderTest.getPatternPlusOne(pat);
//
////    assertThat(result).isEqualTo("FFFFFFFLLR");
//  }
//
//  @Test
//  void testGetSeatId() {
//    // Setup
//    final int row = day06UnderTest.getRow(pattern);
//    final int col = day06UnderTest.getCol(pattern);
//    // Run the test
//    int result = day06UnderTest.getSeatId(row, col);
//
//    // Verify the results
//    assertThat(result).isEqualTo(357);
////     * BFFFBBFRRR: row 70, column 7, seat ID 567.
////        * FFFBBBFRRR: row 14, column 7, seat ID 119.
////        * BBFFBBFRLL: row 102, column 4, seat ID 820.
//
//    String pat = "BFFFBBFRRR";
//    result = day06UnderTest.getMessage(pat);
//    assertThat(result).isEqualTo(567);
//
//    String bin = pat.replaceAll("[FL]", "0").replaceAll("[BR]",
//        "1");
//    assertThat(bin).isEqualTo("1000110111");
//    assertThat(Integer.parseInt(bin, 2)).isEqualTo(567);
//    System.out.println(pat + " : " + bin + " " + Integer.parseInt(bin, 2));
//
//
//    result = day06UnderTest.getSeatId(70, 7);
//    assertThat(result).isEqualTo(567);
//
//    pat = "FFFBBBFRRR";
//    result = day06UnderTest.getMessage(pat);
//    assertThat(result).isEqualTo(119);
//
//    pat = "BBFFBBFRLL";
//    result = day06UnderTest.getMessage(pat);
//    assertThat(result).isEqualTo(820);
//
//    pat = "BBBBBBBLLL";
//    result = day06UnderTest.getMessage(pat);
//    assertThat(result).isEqualTo(1008);
//
//    pat = "BBBBBBBRRR";
//    result = day06UnderTest.getMessage(pat);
//    assertThat(result).isEqualTo(1015);
//
//    pat = "FFFFFFFRRR";
//    result = day06UnderTest.getMessage(pat);
//    assertThat(result).isEqualTo(7);
//
//    pat = "FFFFFFFLLL";
//    result = day06UnderTest.getMessage(pat);
//    assertThat(result).isEqualTo(0);
//
//    pat = "FFFFFFBLLL";
//    result = day06UnderTest.getMessage(pat);
//    assertThat(result).isEqualTo(0);
//
//    pat = "FFFFFBBLLL";
//    result = day06UnderTest.getSeatId(day06UnderTest.getRow(pat),
//        day06UnderTest.getCol(pat));
//    assertThat(result).isEqualTo(16);
//
//    pat = "FFFFFFFLLR";
//    result = day06UnderTest.getSeatId(day06UnderTest.getRow(pat),
//        day06UnderTest.getCol(pat));
//    assertThat(result).isEqualTo(1);
//
//    pat = "FFFFFFFLRL";
//    result = day06UnderTest.getSeatId(day06UnderTest.getRow(pat),
//        day06UnderTest.getCol(pat));
//    assertThat(result).isEqualTo(2);
//
//    pat = "FFFFFFBRRR";
//    result = day06UnderTest.getSeatId(day06UnderTest.getRow(pat),
//        day06UnderTest.getCol(pat));
//    assertThat(result).isEqualTo(7);
//
//    pat = "FFFFFBFRRR";
//    result = day06UnderTest.getSeatId(day06UnderTest.getRow(pat),
//        day06UnderTest.getCol(pat));
//    assertThat(result).isEqualTo(23);
//
//    pat = "FFFFFBBRRR";
//    result = day06UnderTest.getSeatId(day06UnderTest.getRow(pat),
//        day06UnderTest.getCol(pat));
//    assertThat(result).isEqualTo(23);
//
//    pat = "FFFFFBBRRL";
//    result = day06UnderTest.getSeatId(day06UnderTest.getRow(pat),
//        day06UnderTest.getCol(pat));
//    assertThat(result).isEqualTo(22);
//
//    pat = "FFFFFBBRRL";
//    result = day06UnderTest.getSeatId(day06UnderTest.getRow(pat),
//        day06UnderTest.getCol(pat));
//    System.out.println(result);
//
//// start at  pat = "FFFFFFFLLL";
//// go to     pat = "BBBBBBBRRR";
//
//  }

//  @Test
//  void testMain() {
//    // Setup
//
//    // Run the test
//    Day06.main(new String[] {"value"});
//
//    // Verify the results
//  }


}
