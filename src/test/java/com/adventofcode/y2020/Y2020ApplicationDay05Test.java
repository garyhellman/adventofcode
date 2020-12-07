package com.adventofcode.y2020;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Y2020ApplicationDay05Test {

  private Y2020ApplicationDay05 y2020ApplicationDay05UnderTest;
  String pattern = "FBFBBFFRLR";

  @BeforeEach
  void setUp() {
    y2020ApplicationDay05UnderTest = new Y2020ApplicationDay05();
  }

  @Test
  void testRun() {
    // Setup

    // Run the test
    y2020ApplicationDay05UnderTest.run("args");

    // Verify the results
  }

  @Test
  void testGetAll() {
    // Setup

    // Run the test
    ArrayList<String> result = y2020ApplicationDay05UnderTest.makeTotalList();

    // Verify the results
    assertThat(result.size()).isEqualTo(44);
  }


    @Test
  void testGetRow() {
    // Setup

    // Run the test
    int result = y2020ApplicationDay05UnderTest.getRow(pattern);

    // Verify the results
    assertThat(result).isEqualTo(44);

    result = y2020ApplicationDay05UnderTest.getRow("BFFFBBFRRR");
    assertThat(result).isEqualTo(70);
    result = y2020ApplicationDay05UnderTest.getRow("FFFBBBFRRR");
    assertThat(result).isEqualTo(14);
    result = y2020ApplicationDay05UnderTest.getRow("BBFFBBFRLL");
    assertThat(result).isEqualTo(102);


//     * BFFFBBFRRR: row 70, column 7, seat ID 567.
//        * FFFBBBFRRR: row 14, column 7, seat ID 119.
//        * BBFFBBFRLL: row 102, column 4, seat ID 820.
  }

  @Test
  void testGetCol() {
    // Setup

    // Run the test
    int result = y2020ApplicationDay05UnderTest.getCol(pattern);

    // Verify the results
    assertThat(result).isEqualTo(5);
    result = y2020ApplicationDay05UnderTest.getCol("BFFFBBFRRR");
    assertThat(result).isEqualTo(7);
    result = y2020ApplicationDay05UnderTest.getCol("FFFBBBFRRR");
    assertThat(result).isEqualTo(7);
    result = y2020ApplicationDay05UnderTest.getCol("BBFFBBFRLL");
    assertThat(result).isEqualTo(4);


//     * BFFFBBFRRR: row 70, column 7, seat ID 567.
//        * FFFBBBFRRR: row 14, column 7, seat ID 119.
//        * BBFFBBFRLL: row 102, column 4, seat ID 820.
  }

  @Test
  void testGetPatternPlusOne() {
    String pat = "FFFFFFFLLL";
    String result = y2020ApplicationDay05UnderTest.getPatternPlusOne(pat);

//    assertThat(result).isEqualTo("FFFFFFFLLR");
    }

  @Test
  void testGetSeatId() {
    // Setup
    final int row = y2020ApplicationDay05UnderTest.getRow(pattern);
    final int col = y2020ApplicationDay05UnderTest.getCol(pattern);
    // Run the test
    int result = y2020ApplicationDay05UnderTest.getSeatId(row, col);

    // Verify the results
    assertThat(result).isEqualTo(357);
//     * BFFFBBFRRR: row 70, column 7, seat ID 567.
//        * FFFBBBFRRR: row 14, column 7, seat ID 119.
//        * BBFFBBFRLL: row 102, column 4, seat ID 820.

    String pat = "BFFFBBFRRR";
    result = y2020ApplicationDay05UnderTest.getMessage(pat);
    assertThat(result).isEqualTo(567);

    String bin = pat.replaceAll("[FL]", "0").replaceAll("[BR]",
        "1");
    assertThat(bin).isEqualTo("1000110111");
    assertThat(Integer.parseInt(bin, 2)).isEqualTo(567);
    System.out.println(pat + " : " + bin + " " + Integer.parseInt(bin, 2));


    result = y2020ApplicationDay05UnderTest.getSeatId(70, 7);
    assertThat(result).isEqualTo(567);

    pat = "FFFBBBFRRR";
    result = y2020ApplicationDay05UnderTest.getMessage(pat);
    assertThat(result).isEqualTo(119);

    pat = "BBFFBBFRLL";
    result = y2020ApplicationDay05UnderTest.getMessage(pat);
    assertThat(result).isEqualTo(820);

    pat = "BBBBBBBLLL";
    result = y2020ApplicationDay05UnderTest.getMessage(pat);
    assertThat(result).isEqualTo(1008);

    pat = "BBBBBBBRRR";
    result = y2020ApplicationDay05UnderTest.getMessage(pat);
    assertThat(result).isEqualTo(1015);

    pat = "FFFFFFFRRR";
    result = y2020ApplicationDay05UnderTest.getMessage(pat);
    assertThat(result).isEqualTo(7);

    pat = "FFFFFFFLLL";
    result = y2020ApplicationDay05UnderTest.getMessage(pat);
    assertThat(result).isEqualTo(0);

    pat = "FFFFFFBLLL";
    result = y2020ApplicationDay05UnderTest.getMessage(pat);
    assertThat(result).isEqualTo(0);

    pat = "FFFFFBBLLL";
    result = y2020ApplicationDay05UnderTest.getSeatId(y2020ApplicationDay05UnderTest.getRow(pat),
        y2020ApplicationDay05UnderTest.getCol(pat));
    assertThat(result).isEqualTo(16);

    pat = "FFFFFFFLLR";
    result = y2020ApplicationDay05UnderTest.getSeatId(y2020ApplicationDay05UnderTest.getRow(pat),
        y2020ApplicationDay05UnderTest.getCol(pat));
    assertThat(result).isEqualTo(1);

    pat = "FFFFFFFLRL";
    result = y2020ApplicationDay05UnderTest.getSeatId(y2020ApplicationDay05UnderTest.getRow(pat),
        y2020ApplicationDay05UnderTest.getCol(pat));
    assertThat(result).isEqualTo(2);

    pat = "FFFFFFBRRR";
    result = y2020ApplicationDay05UnderTest.getSeatId(y2020ApplicationDay05UnderTest.getRow(pat),
        y2020ApplicationDay05UnderTest.getCol(pat));
    assertThat(result).isEqualTo(7);

    pat = "FFFFFBFRRR";
    result = y2020ApplicationDay05UnderTest.getSeatId(y2020ApplicationDay05UnderTest.getRow(pat),
        y2020ApplicationDay05UnderTest.getCol(pat));
    assertThat(result).isEqualTo(23);

    pat = "FFFFFBBRRR";
    result = y2020ApplicationDay05UnderTest.getSeatId(y2020ApplicationDay05UnderTest.getRow(pat),
        y2020ApplicationDay05UnderTest.getCol(pat));
    assertThat(result).isEqualTo(23);

    pat = "FFFFFBBRRL";
    result = y2020ApplicationDay05UnderTest.getSeatId(y2020ApplicationDay05UnderTest.getRow(pat),
        y2020ApplicationDay05UnderTest.getCol(pat));
    assertThat(result).isEqualTo(22);

    pat = "FFFFFBBRRL";
    result = y2020ApplicationDay05UnderTest.getSeatId(y2020ApplicationDay05UnderTest.getRow(pat),
        y2020ApplicationDay05UnderTest.getCol(pat));
    System.out.println(result);

// start at  pat = "FFFFFFFLLL";
// go to     pat = "BBBBBBBRRR";

  }

  @Test
  void testMain() {
    // Setup

    // Run the test
    Y2020ApplicationDay05.main(new String[] {"value"});

    // Verify the results
  }


}
