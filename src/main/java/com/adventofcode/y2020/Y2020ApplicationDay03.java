package com.adventofcode.y2020;

import ch.qos.logback.classic.Logger;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.javatuples.Pair;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//     public static void main(String[] args) {
//        SpringApplication.run(Y2020Application.class, args);
//    }
@SpringBootApplication
public class Y2020ApplicationDay03 implements CommandLineRunner {

  private static Logger LOG = (Logger) LoggerFactory
      .getLogger(Y2020ApplicationDay03.class);
  private static final int XVALUE = 0;
  private static final int YVALUE = 1;
  private static final String TREE = "#";
  private static final String OPEN = ".";
  private static final String MARKTREE = "X";
  private static final String MARKOPEN = "O";
  final String filename = "src/main/resources/trees.txt";
  final ArrayList<String> result = new ArrayList<>();


  public static void main(String[] args) {
    LOG.info("STARTING THE APPLICATION");
    SpringApplication.run(Y2020ApplicationDay03.class, args);
    LOG.info("APPLICATION FINISHED");
  }

  public static int getNumTrees(Pair<Integer, Integer> slope1, ArrayList<String> result, Pair<Integer, Integer> limit,
                                Pair<Integer, Integer> currentPos) {
    int numTrees = 0;
    String isItaTree;
    if (currentPos.equals(new Pair<>(0, 0))) {
      System.out.println("**************  first pos");
      currentPos = getNewPosition(currentPos, slope1);
    }
    while (positionWithinLimits(currentPos, limit)) {
      isItaTree = getTreeOrOpen(currentPos, result);
      if (isItaTree.equalsIgnoreCase(TREE)) {
//                System.out.println("TREE");
        numTrees++;
      }
//            Pair<Integer, Integer> nextPosition = getNewPosition(currentPos, slope);
//            currentPos = nextPosition;
      currentPos = getNewPosition(currentPos, slope1);
    }
    return numTrees;
  }

  public static boolean positionWithinLimits(Pair<Integer, Integer> currentPos, Pair<Integer, Integer> limit) {
    Integer curX = (Integer) currentPos.getValue(XVALUE);
    Integer curY = (Integer) currentPos.getValue(YVALUE);
    Integer limitX = (Integer) limit.getValue(XVALUE);
    Integer limitY = (Integer) limit.getValue(YVALUE);

    if (curX < limitX && curY < limitY) {
      return true;
    }
    return false;
  }

  public static Pair<Integer, Integer> getNewPosition(Pair<Integer, Integer> currentPos, Pair<Integer, Integer> slope) {
//        System.out.println("getNewPosition ==== ");
    Integer curX = (Integer) currentPos.getValue(XVALUE);
    Integer curY = (Integer) currentPos.getValue(YVALUE);
    Integer slopeX = (Integer) slope.getValue(XVALUE);
    Integer slopeY = (Integer) slope.getValue(YVALUE);
//    System.out.println("getNewPosition ==== " +
//        curX + " " +
//        curY + " " +
//        slopeX + " " +
//        slopeY + " "
//    );
//        if(curX == 0) curX++;
//    if(curY == 0) curY++;
    Pair<Integer, Integer> newPosition = new Pair<>(curX + slopeX, curY + slopeY);
//    System.out.println("getNewPosition ==== "
//        + " " + (Integer) newPosition.getValue(XVALUE)
//        + " " + (Integer) newPosition.getValue(YVALUE)
//        + " " + (Integer) slope.getValue(XVALUE)
//        + " " + (Integer) slope.getValue(YVALUE)
//    );
    return newPosition;
  }

  public static String getTreeOrOpen(Pair<Integer, Integer> currentPos, ArrayList<String> result) {
//        System.out.println("getTreeOrOpen ==== ");
    Integer x = (Integer) currentPos.getValue(XVALUE);
    Integer y = (Integer) currentPos.getValue(YVALUE);

    String mark = result.get(y).substring(x, x + 1);
//        System.out.println(y + " " + x + " " + mark);
//        System.out.println(result.get(y));
    if (mark.equals(OPEN)) {
      System.out.print(result.get(y).substring(0, x));
      System.out.print(MARKOPEN);
      System.out.print(result.get(y).substring(x + 1));
    } else if (mark.equals(TREE)) {
      System.out.print(result.get(y).substring(0, x));
      System.out.print(MARKTREE);
      System.out.print(result.get(y).substring(x + 1));
    }
    System.out.println();

//        System.out.println(result.get(y));
//        System.out.println(result.get(y).substring(x, x + 1));
    return mark;
  }


  @Override
  public void run(String... args) {
    LOG.info("EXECUTING : command line runner");

    for (int i = 0; i < args.length; ++i) {
      LOG.info("args[{}]: {}", i, args[i]);
    }


    int numTrees1 = 0;
    int numTrees2 = 0;
    int numTrees3 = 0;
    int numTrees4 = 0;
    int numTrees5 = 0;
    Pair<Integer, Integer> slope1 = new Pair<>(1, 1);
    Pair<Integer, Integer> slope2 = new Pair<>(3, 1);
    Pair<Integer, Integer> slope3 = new Pair<>(5, 1);
    Pair<Integer, Integer> slope4 = new Pair<>(7, 1);
    Pair<Integer, Integer> slope5 = new Pair<>(1, 2);
//        assertThat((Integer) slope.getValue(XVALUE)).isEqualTo(3);
//        assertThat((Integer) slope.getValue(YVALUE)).isEqualTo(1);
//
//        System.out.println(currentPos.toString());
//        System.out.println(slope.toString());
//
//        currentPos.add(slope);
//        System.out.println(currentPos.toString());
//        System.out.println(slope.toString());


    final ArrayList<String> result = new ArrayList<>();
    try {
      BufferedReader br = new BufferedReader(new FileReader(filename));
      while (br.ready()) {
        String line = br.readLine();
        // depends on slope ...
        StringBuffer sb = new StringBuffer(320);
        int y = 10 * 325 / line.length();
        for (int i = 0; i <= y; i++) {
          sb.append(line);
        }
        result.add(sb.toString());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Pair<Integer, Integer> limit = new Pair<>(result.get(0).length(), result.size());

    int ii = 1;
    for (String line : result) {
      System.out.println("" + ii++ + ":" + line.length() + " " + line);
    }

    String isItaTree = null;

    Pair<Integer, Integer> currentPos = new Pair<>(0, 0);

    numTrees1 = getNumTrees(slope1, result, limit, currentPos);
    numTrees2 = getNumTrees(slope2, result, limit, currentPos);
    numTrees3 = getNumTrees(slope3, result, limit, currentPos);
    numTrees4 = getNumTrees(slope4, result, limit, currentPos);
    numTrees5 = getNumTrees(slope5, result, limit, currentPos);


    System.out.println("Number of Trees: " + numTrees1);
    System.out.println("Number of Trees: " + numTrees2);
    System.out.println("Number of Trees: " + numTrees3);
    System.out.println("Number of Trees: " + numTrees4);
    System.out.println("Number of Trees: " + numTrees5);

    BigDecimal total = BigDecimal.valueOf(Double.valueOf(numTrees1));
    System.out.println("Number of Trees: " + total);
    total = total.multiply(new BigDecimal(numTrees2));
    System.out.println("Number of Trees: " + total);
    total = total.multiply(new BigDecimal(numTrees3));
    System.out.println("Number of Trees: " + total);
    total = total.multiply(new BigDecimal(numTrees4));
    System.out.println("Number of Trees: " + total);
    total = total.multiply(new BigDecimal(numTrees5));
//    System.out.println("Number of Trees: " + Float.valueOf(numTrees1 * numTrees2) );
//    System.out.println("Number of Trees: " + Float.valueOf(numTrees1 * numTrees2 * numTrees3) );
//    System.out.println("Number of Trees: " + Float.valueOf(numTrees1 * numTrees2 * numTrees3 * numTrees4) );
    System.out.println("Number of Trees: " + total);

  }

}

