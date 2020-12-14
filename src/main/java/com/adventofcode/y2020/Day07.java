package com.adventofcode.y2020;

import ch.qos.logback.classic.Logger;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * --- Day 7: Handy Haversacks ---
 * You land at the regional airport in time for your next flight. In fact, it looks like you'll even have time to grab
 * some food: all flights are currently delayed due to issues in luggage processing.
 * <p>
 * Due to recent aviation regulations, many rules (your puzzle input) are being enforced about bags and their contents;
 * bags must be color-coded and must contain specific quantities of other color-coded bags. Apparently, nobody
 * responsible for these regulations considered how long they would take to enforce!
 * <p>
 * For example, consider the following rules:
 * <p>
 * light red bags contain 1 bright white bag, 2 muted yellow bags.
 * dark orange bags contain 3 bright white bags, 4 muted yellow bags.
 * bright white bags contain 1 shiny gold bag.
 * muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
 * shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
 * dark olive bags contain 3 faded blue bags, 4 dotted black bags.
 * vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
 * faded blue bags contain no other bags.
 * dotted black bags contain no other bags.
 * These rules specify the required contents for 9 bag types.
 * In this example, every faded blue bag is empty,
 * every vibrant plum bag contains 11 bags (5 faded blue and 6 dotted black), and so on.
 * <p>
 * You have a shiny gold bag. If you wanted to carry it in at least one other bag,
 * how many different bag colors would be valid for the outermost bag?
 * (In other words: how many colors can, eventually, contain at least one shiny gold bag?)
 * <p>
 * In the above rules, the following options would be available to you:
 * <p>
 * A bright white bag, which can hold your shiny gold bag directly.
 * A muted yellow bag, which can hold your shiny gold bag directly, plus some other bags.
 * A dark orange bag, which can hold bright white and muted yellow bags,
 * either of which could then hold your shiny gold bag.
 * A light red bag, which can hold bright white and muted yellow bags,
 * either of which could then hold your shiny gold bag.
 * So, in this example, the number of bag colors that can eventually contain at least one shiny gold bag is 4.
 * <p>
 * How many bag colors can eventually contain at least one shiny gold bag?
 * (The list of rules is quite long; make sure you get all of it.)
 */

//     public static void main(String[] args) {
//        SpringApplication.run(Y2020Application.class, args);
//    }
@SpringBootApplication
public class Day07 implements CommandLineRunner {

  private static Logger LOG = (Logger) LoggerFactory
      .getLogger(Day07.class);
  final String filename = "src/main/resources/haversack.txt";
  final ArrayList<String> result = new ArrayList<>();


  public static void main(String[] args) {
    LOG.info("STARTING THE APPLICATION");
    SpringApplication.run(Day07.class, args);
    LOG.info("APPLICATION FINISHED");
  }


  @Override
  public void run(String... args) {
    LOG.info("EXECUTING : command line runner");
    final ArrayList<String> result = new ArrayList<>();

//    SortedSet<Integer> seats = new TreeSet<Integer>();

//    readDataAndCreateMap();
//    buildHierarchyTree(root);
//    printHierarchyTree(root, 0);
    int ii = 1;


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

    System.out.println(result.size());
    for (String line2 : result) {
      System.out.println("" + ii++ + ":" + line2.length() + " " + line2.trim());
      line2 = line2.trim();

//      Day07.Haversack haversack = passLineIn(line2);
//      // what should i do with these? put them in a list...?
//      haversackSet.add(haversack);

    }




    }

  public static String convertToTitleCaseIteratingChars(String text) {
    if (text == null || text.isEmpty()) {
      return text;
    }

    StringBuilder converted = new StringBuilder();

    boolean convertNext = true;
    for (char ch : text.toCharArray()) {
      if (Character.isSpaceChar(ch)) {
        convertNext = true;
      } else if (convertNext) {
        ch = Character.toTitleCase(ch);
        convertNext = false;
      } else {
        ch = Character.toLowerCase(ch);
      }
      converted.append(ch);
    }

    return converted.toString();
  }

  public static class MyBag {
    String name;
    //    HashMap<String, ArrayList<HashMap<String, Integer>>> contains; // = new HashMap<>(128);
    ArrayList<HashMap<String, Integer>> contains; // = new HashMap<>(128);

    public MyBag(String name) {
      this.name = name;
      this.contains = new ArrayList<>(128);
    }

    public void addABag(String str, HashMap<String, Integer> x) {
      this.contains.add(x);
    }


    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public ArrayList<HashMap<String, Integer>> getContains() {
      return contains;
    }

    public void setContains(ArrayList<HashMap<String, Integer>> contains) {
      this.contains = contains;
    }

    @Override
    public String toString() {
      StringBuffer sb = new StringBuffer();
      sb.append("MyBag{" + "name='" + name + '\'' +
          ", contains=");

      for (HashMap<String, Integer> hashMap : contains) {
        Iterator iter = hashMap.entrySet().iterator();
        while (iter.hasNext()) {
          Map.Entry entry = (Map.Entry) iter.next();
          sb.append("[Key] : " + entry.getKey() + " [Value] : " + entry.getValue() + " ");
        }
      }
      sb.append(" } ");
      return sb.toString();
    }
  }

  public static class TreeBag {
    String color;
    TreeBag containsLeft;
    int leftCount;
    TreeBag containsRight;
    int rightCount;

    public TreeBag(String inColor) {
      this.color = inColor;
    }

    public String getColor() {
      return color;
    }

    public void setColor(String color) {
      this.color = color;
    }

    public TreeBag getContainsLeft() {
      return containsLeft;
    }

    public int getLeftCount() {
      return leftCount;
    }

    public void setLeftCount(int leftCount) {
      this.leftCount = leftCount;
    }

    public TreeBag getContainsRight() {
      return containsRight;
    }

    public void setContainsRight(TreeBag containsRight) {
      this.containsRight = containsRight;
    }

    public int getRightCount() {
      return rightCount;
    }

    public void setRightCount(int rightCount) {
      this.rightCount = rightCount;
    }

    public void setContainsRight(TreeBag treebag, int i) {
      this.containsRight = treebag;
      this.rightCount = i;
    }

    public void setContainsLeft(TreeBag treebag, int i) {
      this.containsLeft = treebag;
      this.leftCount = i;
    }
  }


  public static class Haversack {
    private String color;
    private Map<String,Integer> hashMap;

    public Haversack(String color) {
      this.color = color;
      this.hashMap = new HashMap<>(8);
    }

    public String getColor() {
      return color;
    }

    public void setColor(String color) {
      this.color = color;
    }

    public Map<String, Integer> getHashMap() {
      return hashMap;
    }

    public void setHashMap(Map<String, Integer> hashMap) {
      this.hashMap = hashMap;
    }
  }

  public static int findBagColorCount(TreeBag root){
    System.out.println(root.getColor());
    if (root!= null) {
      if (root.getContainsLeft() != null || root.getContainsRight() != null) {
        return 1 +
            findBagColorCount(root.getContainsLeft()) +
                findBagColorCount(root.getContainsRight());
      }
    }
    return 0;
  }

  public static int bagsThatContain(ArrayList<MyBag> bags, String inStr) {
    String bagColor = getColor(inStr);
    System.out.println("bagsThatContain: " + bags.size() + " : " + bagColor);
    int retVal = 0;
    for (MyBag bag : bags) {
      ArrayList<HashMap<String, Integer>> contains = bag.getContains();
      for(HashMap<String, Integer> hashMap: contains) {
        Iterator iter = hashMap.entrySet().iterator();
        while (iter.hasNext()) {
          Map.Entry entry = (Map.Entry) iter.next();
          if(bagColor.equalsIgnoreCase((String) entry.getKey())) {
            System.out.println("**************  it contains.");
          }
        }
      }

      System.out.println(bag.toString());
      if(bag.getName().equalsIgnoreCase(bagColor)) {
        System.out.println("******");
      }
    }
//    System.out.println("bags to string: " + bags.toString());

    return retVal;
  }


  //  @org.jetbrains.annotations.NotNull
  public static String getColor(String firstPart) {
    String[] words = firstPart.split(" ");
    String color =
        words[0] + words[1].substring(0, 1).toUpperCase(Locale.ROOT) + words[1].substring(1).toLowerCase(Locale.ROOT);
    return color;
  }

}


