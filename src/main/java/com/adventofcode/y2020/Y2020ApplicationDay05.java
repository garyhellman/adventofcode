package com.adventofcode.y2020;

import ch.qos.logback.classic.Logger;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * --- Day 5: Binary Boarding ---
 * You board your plane only to discover a new problem: you dropped your boarding pass!
 * You aren't sure which seat is yours, and all of the flight attendants are busy with
 * the flood of people that suddenly made it through passport control.
 *
 * You write a quick program to use your phone's camera to scan all of the nearby boarding passes (your puzzle input);
 * perhaps you can find your seat through process of elimination.
 *
 * Instead of zones or groups, this airline uses binary space partitioning to seat people.
 * A seat might be specified like FBFBBFFRLR, where F means "front", B means "back", L means "left", and R means "right".
 *
 * The first 7 characters will either be F or B;
 * these specify exactly one of the 128 rows on the plane (numbered 0 through 127).
 * Each letter tells you which half of a region the given seat is in.
 * Start with the whole list of rows;
 * the first letter indicates whether the seat is in the front (0 through 63) or the back (64 through 127).
 * The next letter indicates which half of that region the seat is in, and so on until you're left with exactly one row.
 *
 * For example, consider just the first seven characters of FBFBBFFRLR:
 *
 * Start by considering the whole range, rows 0 through 127.
 * F means to take the lower half, keeping rows 0 through 63.
 * B means to take the upper half, keeping rows 32 through 63.
 * F means to take the lower half, keeping rows 32 through 47.
 * B means to take the upper half, keeping rows 40 through 47.
 * B keeps rows 44 through 47.
 * F keeps rows 44 through 45.
 * The final F keeps the lower of the two, row 44.
 * The last three characters will be either L or R;
 * these specify exactly one of the 8 columns of seats on the plane (numbered 0 through 7).
 * The same process as above proceeds again, this time with only three steps.
 * L means to keep the lower half, while R means to keep the upper half.
 *
 * For example, consider just the last 3 characters of FBFBBFFRLR:
 *
 * Start by considering the whole range, columns 0 through 7.
 * R means to take the upper half, keeping columns 4 through 7.
 * L means to take the lower half, keeping columns 4 through 5.
 * The final R keeps the upper of the two, column 5.
 * So, decoding FBFBBFFRLR reveals that it is the seat at row 44, column 5.
 *
 * Every seat also has a unique seat ID: multiply the row by 8, then add the column.
 * In this example, the seat has ID 44 * 8 + 5 = 357.
 *
 * Here are some other boarding passes:
 *
 * BFFFBBFRRR: row 70, column 7, seat ID 567.
 * FFFBBBFRRR: row 14, column 7, seat ID 119.
 * BBFFBBFRLL: row 102, column 4, seat ID 820.
 * As a sanity check, look through your list of boarding passes. What is the highest seat ID on a boarding pass?
 *
 *
 * Your puzzle answer was 885.
 *
 * The first half of this puzzle is complete! It provides one gold star: *
 *
 * --- Part Two ---
 * Ding! The "fasten seat belt" signs have turned on. Time to find your seat.
 *
 * It's a completely full flight, so your seat should be the only missing boarding pass in your list. However, there's a catch: some of the seats at the very front and back of the plane don't exist on this aircraft, so they'll be missing from your list as well.
 *
 * Your seat wasn't at the very front or back, though; the seats with IDs +1 and -1 from yours will be in your list.
 *
 * What is the ID of your seat?
 *
 */

//     public static void main(String[] args) {
//        SpringApplication.run(Y2020Application.class, args);
//    }
@SpringBootApplication
public class Y2020ApplicationDay05 implements CommandLineRunner {

  private static Logger LOG = (Logger) LoggerFactory
      .getLogger(Y2020ApplicationDay05.class);
  final String filename = "src/main/resources/boardingpasses.txt";
  final ArrayList<String> result = new ArrayList<>();


  public static void main(String[] args) {
    LOG.info("STARTING THE APPLICATION");
    SpringApplication.run(Y2020ApplicationDay05.class, args);
    LOG.info("APPLICATION FINISHED");
  }



  @Override
  public void run(String... args) {
    LOG.info("EXECUTING : command line runner");
    final ArrayList<String> result = new ArrayList<>();

    SortedSet<Integer> seats = new TreeSet<Integer>();


    int numberPassports = 0;
    int max = 0;
    try {
      BufferedReader br = new BufferedReader(new FileReader(filename));
      while (br.ready()) {
        String line = br.readLine();
//        if(line.isEmpty()) numberPassports++;

        String bin = line.replaceAll("[FL]", "0").replaceAll("[BR]",
            "1");
        System.out.println(line + " : " + bin + " " + Integer.parseInt(bin, 2));
//        System.out.println(bin);

        seats.add(Integer.parseInt(bin, 2));
        max = Math.max(max, seats.last());




        result.add(line);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println(result.size());

    // part 1
    System.out.println("max seat id: " + max);

    int seat = max;
    while (seats.contains(seat)) {
      seat--;
    }

// part 2
    System.out.println("missing seat: " + seat);



//    assert (result.size() == 1000);

//    for (int i = 0; i < args.length; ++i) {
//      LOG.info("args[{}]: {}", i, args[i]);
//    }
//    int out = extracted(result);
//    // #1 guess - 207
//    System.out.println(result.size());
//    System.out.println("total number of passports: " + numberPassports);
//    System.out.println("# of valid Passports: " + out);
// #2 guess 168 too high
    // 165 too low
    ArrayList<Integer> seatList = new ArrayList<>(900);
    Set<Integer> integerSet = new HashSet<>(900);
    int maxSeatId = 0;
    int minSeatId = 0;
    for (String pattern: result) {
      int seatId = getMessage(pattern);
      seatList.add(seatId);
      integerSet.add(seatId);
      if(seatId > maxSeatId) {
        maxSeatId = seatId;
      }
      if(seatId <= maxSeatId) {
        minSeatId = seatId;
      }
    }
//    Collections.sort(seatList);


    System.out.println("Max SeatId: " + maxSeatId);
    System.out.println("Min SeatId: " + minSeatId);

    int seat0 = maxSeatId;

    for(int i=0;i<=127;i++) {
      for(int j=0;j<=7;j++) {
        int k = i*8+j;

        if(integerSet.contains(k)) {
        System.out.println("-"+k);
        } else {
          System.out.println("*** "+ k);
        }


//        System.out.println("add???: " + k);
//        if(k > minSeatId && k < maxSeatId) {
//          System.out.println("added: " + k);
//          integerSet.add(k);
//        }

////      for(int i=minSeatId;i<=maxSeatId;i++) {
////      for(int i=0;i<=1015;i++) {
////      System.out.println(i);
//      if(seatList.contains(k)) {
////        System.out.println("-"+i);
//      } else {
//        System.out.println("*** "+k);
//      }
      }
    }

//    Iterator<Integer> itr = integerSet.iterator();
//    while(itr.hasNext()){
//      Integer iii = itr.next();
////      System.out.println(iii);
//      if(seatList.contains(iii)) {
////        System.out.println("-"+i);
//      } else {
//        System.out.println("*** "+ iii);
//      }
//    }

//    while(seatList.contains(seat)) {
//      seat--;
//    }
//    seat--;
//
//    while(seatList.contains(seat)) {
//      seat--;
//    }

    System.out.println("My SeatId: " + seat0);
  }

  /**
   * * For example, consider just the first seven characters of FBFBBFFRLR:
   *  *
   *  * Start by considering the whole range, rows 0 through 127.
   *  * F means to take the lower half, keeping rows 0 through 63.
   *  * B means to take the upper half, keeping rows 32 through 63.
   *  * F means to take the lower half, keeping rows 32 through 47.
   *  * B means to take the upper half, keeping rows 40 through 47.
   *  * B keeps rows 44 through 47.
   *  * F keeps rows 44 through 45.
   *  * The final F keeps the lower of the two, row 44.
   *  * The last three characters will be either L or R;
   *  * these specify exactly one of the 8 columns of seats on the plane (numbered 0 through 7).
   *  * The same process as above proceeds again, this time with only three steps.
   *  * L means to keep the lower half, while R means to keep the upper half.
   *  *
   *  * For example, consider just the last 3 characters of FBFBBFFRLR:
   *  *
   *  * Start by considering the whole range, columns 0 through 7.
   *  * R means to take the upper half, keeping columns 4 through 7.
   *  * L means to take the lower half, keeping columns 4 through 5.
   *  * The final R keeps the upper of the two, column 5.
   *  * So, decoding FBFBBFFRLR reveals that it is the seat at row 44, column 5.
   *  *
   * @param input
   * @return
   */
  public int getRow(String input) {
    int lowerRow = 0;
    int mid = 0;
    int upperRow = 127;
//    int whole = 128;
    int retVal = 0;
    String[] letters = input.substring(0,6).split("", 10);
//    System.out.println(letters.length);
/**
 *    *  * Start by considering the whole range, rows 0 through 127.
 *    *  * F means to take the lower half, keeping rows 0 through 63.
 *    *  * B means to take the upper half, keeping rows 32 through 63.
 *    *  * F means to take the lower half, keeping rows 32 through 47.
 *    *  * B means to take the upper half, keeping rows 40 through 47.
 *    *  * B keeps rows 44 through 47.
 *    *  * F keeps rows 44 through 45.
 *    *  * The final F keeps the lower of the two, row 44.
 *    *  * The last three characters will be either L or R;
 */
//    System.out.println("" + " lower: " + lowerRow + " upper: " + upperRow);

    for (String s: letters) {
      mid = (upperRow-lowerRow)/2 + lowerRow;
//      System.out.println("mid: " + mid);
      switch(s) {
        case "F": // lower half
//          System.out.println(whole/2);
          lowerRow = lowerRow;
          upperRow = mid;
          break;
        case "B": // upper half
          upperRow = upperRow;
          lowerRow = mid + 1;
          break;
      }
//      System.out.println(s + " lowerRow: " + lowerRow + " upper: " + upperRow);
    }
    return mid;
  }

  public int getCol(String input) {
    int lowerRow = 0;
    int mid = 0;
    int upperRow = 7;
    int retVal = 0;
    String[] letters = input.substring(7).split("", 10);
//    System.out.println(letters.length);
/**
 *  *
 *  * Start by considering the whole range, columns 0 through 7.
 *  * R means to take the upper half, keeping columns 4 through 7.
 *  * L means to take the lower half, keeping columns 4 through 5.
 *  * The final R keeps the upper of the two, column 5.
 *  * So, decoding FBFBBFFRLR reveals that it is the seat at row 44, column 5.
 */
//    System.out.println("" + " lower: " + lowerRow + " upper: " + upperRow);

    for (String s: letters) {
      mid = (upperRow-lowerRow)/2 + lowerRow;
//      System.out.println("mid: " + mid);
      switch(s) {
        case "L": // lower half
//          System.out.println(whole/2);
          lowerRow = lowerRow;
          upperRow = mid;
          break;
        case "R": // upper half
          upperRow = upperRow;
          lowerRow = mid + 1;
          break;
      }
//      System.out.println(s + " lowerRow: " + lowerRow + " upper: " + upperRow);
    }
    return mid;
  }

  int getSeatId(int row, int col) {
//    Every seat also has a unique seat ID: multiply the row by 8, then add the column.
//        * In this example, the seat has ID 44 * 8 + 5 = 357.
    return row*8 + col;

  }

  int getMessage(String pattern) {
    final int row = this.getRow(pattern);
    final int col = this.getCol(pattern);
    final int result = this.getSeatId(row, col);
    return result;
  }

  public ArrayList<String> makeTotalList() {
    ArrayList<String> retVal = new ArrayList<>(900);

    StringBuffer sb = new StringBuffer();
    for(int i=0;i<7;i++) {
      for(int j=0;j<2;j++) {
        if(j==0) sb.append("F");
        if(j==1) sb.append("B");
      }
      System.out.println(sb.toString());
      retVal.add(sb.toString());
    }
    return retVal;
  }

  public String getPatternPlusOne(String pattern) {
    String retVal = pattern;
    // get
    return retVal;
  }

}


