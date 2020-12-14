package com.adventofcode.y2020;

import ch.qos.logback.classic.Logger;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * --- Day 6: Custom Customs ---
 * As your flight approaches the regional airport where you'll switch to a much larger plane, customs declaration
 * forms are distributed to the passengers.
 *
 * The form asks a series of 26 yes-or-no questions marked a through z. All you need to do is identify the questions
 * for which anyone in your group answers "yes". Since your group is just you, this doesn't take very long.
 *
 * However, the person sitting next to you seems to be experiencing a language barrier and asks if you can help.
 * For each of the people in their group, you write down the questions for which they answer "yes", one per line.
 * For example:
 *
 * abcx
 * abcy
 * abcz
 * In this group, there are 6 questions to which anyone answered "yes": a, b, c, x, y, and z.
 * (Duplicate answers to the same question don't count extra; each question counts at most once.)
 *
 * Another group asks for your help, then another, and eventually you've collected answers from every group
 * on the plane (your puzzle input). Each group's answers are separated by a blank line, and within each group,
 * each person's answers are on a single line. For example:
 *
 * abc
 *
 * a
 * b
 * c
 *
 * ab
 * ac
 *
 * a
 * a
 * a
 * a
 *
 * b
 * This list represents answers from five groups:
 *
 * The first group contains one person who answered "yes" to 3 questions: a, b, and c.
 * The second group contains three people; combined, they answered "yes" to 3 questions: a, b, and c.
 * The third group contains two people; combined, they answered "yes" to 3 questions: a, b, and c.
 * The fourth group contains four people; combined, they answered "yes" to only 1 question, a.
 * The last group contains one person who answered "yes" to only 1 question, b.
 * In this example, the sum of these counts is 3 + 3 + 3 + 1 + 1 = 11.
 *
 * For each group, count the number of questions to which anyone answered "yes". What is the sum of those counts?
 *
 *
 * --- Part Two ---
 * As you finish the last group's customs declaration, you notice that you misread one word in the instructions:
 *
 * You don't need to identify the questions to which anyone answered "yes";
 * you need to identify the questions to which everyone answered "yes"!
 *
 * Using the same example as above:
 *
 * abc
 *
 * a
 * b
 * c
 *
 * ab
 * ac
 *
 * a
 * a
 * a
 * a
 *
 * b
 * This list represents answers from five groups:
 *
 * In the first group, everyone (all 1 person) answered "yes" to 3 questions: a, b, and c.
 * In the second group, there is no question to which everyone answered "yes".
 * In the third group, everyone answered yes to only 1 question, a. Since some people did not
 * answer "yes" to b or c, they don't count.
 * In the fourth group, everyone answered yes to only 1 question, a.
 * In the fifth group, everyone (all 1 person) answered "yes" to 1 question, b.
 * In this example, the sum of these counts is 3 + 0 + 1 + 1 + 1 = 6.
 *
 * For each group, count the number of questions to which everyone answered "yes". What is the sum of those counts?
 *
 *
 */

//     public static void main(String[] args) {
//        SpringApplication.run(Y2020Application.class, args);
//    }
@SpringBootApplication
public class Day06 implements CommandLineRunner {

  private static Logger LOG = (Logger) LoggerFactory
      .getLogger(Day06.class);
  final String filename = "src/main/resources/puzzle.txt";
  final ArrayList<String> result = new ArrayList<>();


//  public static void main(String[] args) {
//    LOG.info("STARTING THE APPLICATION");
//    SpringApplication.run(Day06.class, args);
//    LOG.info("APPLICATION FINISHED");
//  }



  @Override
  public void run(String... args) {
    LOG.info("EXECUTING : command line runner");
    final ArrayList<String> result = new ArrayList<>();

//    SortedSet<Integer> seats = new TreeSet<Integer>();


//    int numberPassports = 0;
//    int max = 0;
    try {
      BufferedReader br = new BufferedReader(new FileReader(filename));
      while (br.ready()) {
        String line = br.readLine();
//        if(line.isEmpty()) numberPassports++;

//        String bin = line.replaceAll("[FL]", "0").replaceAll("[BR]",
//            "1");
//        System.out.println(line + " : " + bin + " " + Integer.parseInt(bin, 2));
//        System.out.println(bin);

//        seats.add(Integer.parseInt(bin, 2));
//        max = Math.max(max, seats.last());

        result.add(line);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println(result.size());

    // part 1
//    System.out.println("max seat id: " + max);
//
//    int seat = max;
//    while (seats.contains(seat)) {
//      seat--;
//    }

// part 2
//    System.out.println("missing seat: " + seat);



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
//    ArrayList<Integer> seatList = new ArrayList<>(900);
//    Set<Integer> integerSet = new HashSet<>(900);
//    int maxSeatId = 0;
//    int minSeatId = 0;
//    for (String pattern: result) {
//      int seatId = getMessage(pattern);
//      seatList.add(seatId);
//      integerSet.add(seatId);
//      if(seatId > maxSeatId) {
//        maxSeatId = seatId;
//      }
//      if(seatId <= maxSeatId) {
//        minSeatId = seatId;
//      }
//    }
//    Collections.sort(seatList);


//    System.out.println("Max SeatId: " + maxSeatId);
//    System.out.println("Min SeatId: " + minSeatId);
//
//    int seat0 = maxSeatId;
//
//    for(int i=0;i<=127;i++) {
//      for(int j=0;j<=7;j++) {
//        int k = i*8+j;
//
//        if(integerSet.contains(k)) {
//          System.out.println("-"+k);
//        } else {
//          System.out.println("*** "+ k);
//        }


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
//      }
//    }

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

//    System.out.println("My SeatId: " + seat0);
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


