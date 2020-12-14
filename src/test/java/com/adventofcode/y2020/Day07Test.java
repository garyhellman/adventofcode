package com.adventofcode.y2020;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.adventofcode.y2020.Day07.bagsThatContain;
import static org.assertj.core.api.Assertions.assertThat;

class Day07Test {

  private Day07 Day07UnderTest;
//  String pattern = "FBFBBFFRLR";

  Set<Day07.Haversack> haversackSet = new HashSet<>(128);
  static Map<String, HaversackNode> haversacks = new HashMap<>(32);
  HaversackNode root;

//  static Map<String, HaversackNode> haversacks;
//  static HaversackNode root;

  @BeforeEach
  void setUp() {
    Day07UnderTest = new Day07();
  }

  @Test
  void testRun() {
    // Setup

    // Run the test
    Day07UnderTest.run("args");

    // Verify the results
  }

  @Test
  void testGetAll() {
    // Setup

    // Run the test
//    ArrayList<String> result = Day07UnderTest.makeTotalList();
//
//    // Verify the results
//    assertThat(result.size()).isEqualTo(44);
  }


  @Test
  void testNumberofGroups() {
    final String filename = "src/main/resources/haversacks.txt";
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

    assertThat(result.size()).isEqualTo(594);
//    System.out.println(result.size());

  }

  @Test
  void testNumberofGroupsAlso() {
    final String filename = "src/main/resources/haversacks.txt";
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
    final String filename = "src/main/resources/haversacks.txt";
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
        {"light red bags contain 1 bright white bag, 2 muted yellow bags.\n",
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags.\n",
            "bright white bags contain 1 shiny gold bag.\n",
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.\n",
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.\n",
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags.\n",
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.\n",
            "faded blue bags contain no other bags.\n",
            "dotted black bags contain no other bags.\n"
        };

    int totalGroups = 0;
    int bagss = 0;
    final ArrayList<String> result = new ArrayList<>();
//    final ArrayList<String> bags = new ArrayList<>();
//    final HashMap<String, ArrayList<HashMap<String, Integer>>> myBags = new HashMap<>(128);
    ArrayList<Day07.MyBag> bags = new ArrayList<>(128);
//    HashMap<String, Integer> bag;

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

      // regex for adjective || color || bag(s)
      String[] things = line2.split("contain");
      String firstPart = things[0];
      String secondPart = things[1];
//      System.out.println(things.length);
//      System.out.println(firstPart);
//      System.out.println(secondPart);


      String color = Day07.getColor(firstPart);
//      System.out.println("The color of bag: " + color);
      // make a mybag
//      bags.add(new Day07.MyBag(color));
      Day07.MyBag myBag = new Day07.MyBag(color);
//      System.out.println(myBag.toString());

      myBag = getOtherBags(myBag, color, secondPart);
      System.out.println(myBag.toString());
//      System.out.println(Arrays.stream(otherWords).findFirst());
      bags.add(myBag);

    }
//      String regex1 = "^(.*)(\\d+)(.*)(\\d+)(.*)$";
//      String regex1 = "^(?<adjective>.*)bags$";
//      Matcher matcher = Pattern.compile(regex1).matcher(firstPart);
//
//      if (matcher.matches()) {
//        String matchedGroup = matcher.group("adjective");
//        System.out.println(matchedGroup);
//      } else {
//        System.out.println("no match");
//      }
//
//      recordStarted = true;
//      if (line2.isBlank()) {
//        System.out.println("new bags");
//        bagss++;
//        int nn = processGroups(bags);
//        System.out.println("answers in that bags: " + nn);
//        totalGroups += nn;
//        bags.clear();
//        recordStarted = false;
//      }
//
//      bags.add(line2);
//    }
//
//    if (recordStarted) {
//      System.out.println("last record not finished");
//      bagss++;
//      int nn = processGroups(bags);
//      totalGroups += nn;
//    }

    assertThat(bags.size()).isEqualTo(9);
    assertThat(bagsThatContain(bags, "shiny gold bag")).isEqualTo(4);

  }

  private Day07.MyBag getOtherBags(Day07.MyBag bag, String key, String secondPart) {
//    System.out.println("getOtherBags: " + secondPart);
    boolean isComma = false;
    String[] bagParts = null;
    if (secondPart.indexOf(',') > 0) {
//      System.out.println("found a comma");
      isComma = true;
      bagParts = secondPart.split(",");
      for (String s : bagParts) {
        bag.addABag(key, extracted(s));


      }
    } else {
//      System.out.println("no comma");
      bag.addABag(key, extracted(secondPart));
//      extracted(secondPart);
    }

    String[] secondParts = secondPart.split(" ");
//    List<String> resultList =
//        Arrays.stream(secondParts).map(element -> convertToTitleCaseIteratingChars(element)).collect(Collectors.toList());
////      System.out.println(resultList.size());
//    for (String s : resultList) {
//      System.out.println(s);
//    }
    return bag;
  }

  private HashMap<String, Integer> extracted(String s) {
    HashMap<String, Integer> hashMap = new HashMap<>(2);
    s = s.trim();
//    System.out.println("                     :" + s);

//    System.out.println("test regex ===>");
//    Pattern p = Pattern.compile("\\w+ (\\d+) [\\w ]+ (\\d{2}:\\d{2})");
//    Matcher m = p.matcher(s);
//    if(m.matches()) {
//      System.out.println("The quantity is " + m.group(1));
//      System.out.println("The time is " + m.group(2));
//    }
//    System.out.println("=== test regex");

    String[] words = s.split(" ");
    String color =
        words[1] + words[2].substring(0, 1).toUpperCase(Locale.ROOT) + words[2].substring(1).toLowerCase(Locale.ROOT);
    if (words[0].equalsIgnoreCase("no")) {
      words[0] = "0";
    }
    if (color.equalsIgnoreCase("otherbags.")) {
      color = "otherBag";
    }
//    System.out.println(" bag(s): " + words[0] + " " + color);
    hashMap.put(color, Integer.valueOf(words[0]));
//        System.out.println("The color of bag: " + color);
    return hashMap;
  }


  private HaversackNode extractedV2(String ownerColor, String s) {
    s = s.trim();
    System.out.println("                     :" + s);

    String[] words = s.split(" ");
    String color =
        words[1] + words[2].substring(0, 1).toUpperCase(Locale.ROOT) + words[2].substring(1).toLowerCase(Locale.ROOT);
    if (words[0].equalsIgnoreCase("no")) {
      words[0] = "0";
    }
    if (color.equalsIgnoreCase("otherbags.")) {
      color = "otherBag";
    }
//    System.out.println(" bag(s): " + words[0] + " " + color);
//    hashMap.put(color, Integer.valueOf(words[0]));
//        System.out.println("The color of bag: " + color);


    HaversackNode haversackNode = new HaversackNode(color, Integer.valueOf(words[0]), ownerColor);
    return haversackNode;
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

  @Test
  void testMain() {
    // Setup

    // Run the test
    Day07.main(new String[] {"value"});

    // Verify the results
  }

  @Test
  void testRegex() {
    System.out.println("start");
    Pattern pattern = null;
    Matcher matcher = null;
//    "light red bags contain 1 bright white bag, 2 muted yellow bags.\n",
//        "dark orange bags contain 3 bright white bags, 4 muted yellow bags.\n",
//        "bright white bags contain 1 shiny gold bag.\n",
//        "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.\n",
//        "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.\n",
//        "dark olive bags contain 3 faded blue bags, 4 dotted black bags.\n",
//        "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.\n",
//        "faded blue bags contain no other bags.\n",
//        "dotted black bags contain no other bags.\n"
    String regex = "(\\w+ (\\d+)\\bbag|bags\\b)";
    String input = "light red bags contain 1 bright white bag, 2 muted yellow bags.";
    try {
      pattern = Pattern.compile(regex);

      matcher = pattern.matcher(input);
    } catch (PatternSyntaxException pse) {
      System.out.println("There is a problem" +
          " with the regular expression!%n");
      System.out.println("The pattern in question is: " + pse.getPattern());
      System.out.println("The description is: " + pse.getDescription());
      System.out.println("The message is: " + pse.getMessage());
      System.out.println("The index is:     " + pse.getIndex());
    }

//    while (matcher.find()) {
//      System.out.println("" + matcher.group() + " " + matcher.start() + " " + matcher.end());
//    }
    int count = 0;
    while (matcher.find()) {
      count++;
      System.out.println("Match number " + count);
      System.out.println(matcher.group(count));
      System.out.println("start(): " + matcher.start());
      System.out.println("end(): " + matcher.end());
      System.out.println(input.substring(matcher.start(), matcher.end()));
    }
  }

//    "vibrant orange bags contain 1 faded silver bag.\n",
//        "plaid lime bags contain 4 shiny olive bags, 5 dim olive bags, 1 dotted gray bag, 2 clear black bags.\n",
//        "pale green bags contain 2 plaid silver bags, 5 dark blue bags, 1 muted blue bag, 3 posh white bags.\n",
//        "dotted brown bags contain 3 dark bronze bags, 5 posh fuchsia bags, 3 plaid cyan bags.\n",
//        "muted yellow bags contain 2 vibrant fuchsia bags, 4 striped brown bags, 3 muted tan bags, 1 shiny plum bag.\n",
//        "light crimson bags contain 3 bright lavender bags, 1 shiny olive bag.\n",
//        "striped black bags contain 2 drab aqua bags, 1 clear black bag, 5 mirrored teal bags, 3 dark chartreuse bags.\n",
//        "faded olive bags contain 2 drab aqua bags.\n",
//        "clear black bags contain no other bags.\n",


  /**
   * final String filename = "src/main/resources/haversacks.txt";
   * final ArrayList<String> result = new ArrayList<>();
   * final ArrayList<String> group = new ArrayList<>();
   * <p>
   * boolean recordStarted = false;
   * recordStarted = true;
   * int totalGroups = 0;
   * int groups = 0;
   * <p>
   * try {
   * BufferedReader br = new BufferedReader(new FileReader(filename));
   * while (br.ready()) {
   * String line = br.readLine();
   * recordStarted = true;
   * if (line.isEmpty()) {
   * //          System.out.println("new group");
   * groups++;
   * int nn = processGroups(group);
   * totalGroups += nn;
   * group.clear();
   * recordStarted = false;
   * }
   * group.add(line);
   * result.add(line);
   * }
   * if (recordStarted) {
   * System.out.println("last record not finished");
   * groups++;
   * int nn = processGroups(group);
   * totalGroups += nn;
   * }
   * <p>
   * } catch (FileNotFoundException e) {
   * e.printStackTrace();
   * } catch (IOException e) {
   * e.printStackTrace();
   * }
   * <p>
   * assertThat(groups).isEqualTo(487);
   * System.out.println(result.size());
   */


  @Test
  void testTreeBag() {
/*    String[] lines =
        {"light red bags contain 1 bright white bag, 2 muted yellow bags.\n",
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags.\n",
            "bright white bags contain 1 shiny gold bag.\n",
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.\n",
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.\n",
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags.\n",
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.\n",
            "faded blue bags contain no other bags.\n",
            "dotted black bags contain no other bags.\n"
        };

    int totalGroups = 0;
    int bagss = 0;
    final ArrayList<String> result = new ArrayList<>();
//    final ArrayList<String> bags = new ArrayList<>();
//    final HashMap<String, ArrayList<HashMap<String, Integer>>> myBags = new HashMap<>(128);
//    ArrayList<Day07.MyBag> bags = new ArrayList<>(128);
//    HashMap<String, Integer> bag;

    for (int i = 0; i < lines.length; i++) {
      String line = lines[i];
//      System.out.println("" + i + ":" + line.trim().length() + " " + line.trim());
      result.add(line);
//      System.out.println(line);
    }
*/

    ///////////////////////
    final String filename = "src/main/resources/haversacks.txt";
    final ArrayList<String> result = new ArrayList<>();

    boolean recordStarted = false;
    recordStarted = true;

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

    ///////////////////////
    int ii = 1;

    HaversackNode haversack = null;

//    Set<Day07.Haversack> haversackSet = new HashSet<>(128);
    for (String line2 : result) {
      System.out.println("" + ii++ + ":" + line2.length() + " " + line2.trim());
      line2 = line2.trim();


//      Day07.Haversack haversack = passLineIn(line2);
      haversack = passLineInV2(line2);
      System.out.println(haversack.toString());
//      // what should i do with these? put them in a list...?
//      haversackSet.add(haversack);

      haversacks.put(haversack.getColor(), haversack);
      if (haversack.getSubordinates() == null) {
        System.out.println("Root");
        root = haversack;
      }
    }

    String findColor = "shinyGold";
    System.out.println("Looking for: " + findColor + " ::::::::::::::::::::::::::::::::::::::  ");


    int countFind = 0;
    // iterate through hashmap - lookup each subordinate from hashmap
//    {
    for (Map.Entry<String, HaversackNode> entry : haversacks.entrySet()) {
      HaversackNode bagsContainer = entry.getValue();
      String color = entry.getKey();

//      String color = "lightRed";  // 1
//      String color = "darkOrange"; // 1
//      String color = "brightWhite"; // 1
//      String color = "mutedYellow"; // 1
//      String color = "fadedBlue"; // 0
//      String color = "vibrantPlum"; // 0
//      String color = "dottedBlack"; // 0
//      String color = "darkOlive"; // 0
//      String color = "darkOlive";
//      String color = "shinyGold"; // 0

//      HaversackNode bagsContainer = getNodeById(color);
      //do something with the key and value
      System.out.println("       ::: " + color + " :: " + haversacks.get(color).getSubordinates());
      countFind = +countBag(findColor, countFind, bagsContainer);
      System.out.println("countFind: " + countFind);
      System.out.println();

//      if (color.equalsIgnoreCase(findColor)) {
//        System.out.println("       *** ***");
//      }
    }

    System.out.println(countFind);


    buildHierarchyTree(root);

    // regex for adjective || color || bag(s)
//      String[] things = line2.split("contain");
//      String firstPart = things[0];
//      String secondPart = things[1];
//      System.out.println(things.length);
//      System.out.println(firstPart);
//      System.out.println(secondPart);


//      String color = Day07.getColor(firstPart);


//      System.out.println("The color of bag: " + color);
    // make a mybag
//      bags.add(new Day07.MyBag(color));
//      Day07.MyBag myBag = new Day07.MyBag(color);
//      System.out.println(myBag.toString());

//      myBag = getOtherBags(myBag, color, secondPart);
//      System.out.println(myBag.toString());
//      System.out.println(Arrays.stream(otherWords).findFirst());
//      bags.add(myBag);

//    }

//    assertThat(haversackSet.size()).isEqualTo(9);
//    assertThat(haversackSet.contains(new Day07.Haversack("clearBlack")));
//    assertThat(haversackSet.contains(new Day07.Haversack("dottedBlack")));

    assertThat(haversacks.size()).isEqualTo(594);


    Iterator<Day07.Haversack> itr = haversackSet.iterator();
    while (itr.hasNext()) {
      Day07.Haversack haversack0 = itr.next();
      System.out.println("... " + haversack0.getColor());

      if (haversack0.getHashMap().containsKey(findColor)) {
        System.out.println("*** " + haversack0.getColor());
      }

      for (Map.Entry<String, Integer> entry : haversack0.getHashMap().entrySet()) {
        Integer count = entry.getValue();
        String color = entry.getKey();
        //do something with the key and value
        System.out.println("       ::: " + color);
        if (color.equalsIgnoreCase(findColor)) {
          System.out.println("       *** ***");
        }
      }

    }

    long countBags1 = haversackSet.stream()
        .filter(h -> h.getHashMap().containsKey(findColor))
        .count();

    System.out.println(countBags1);
    assertThat(countBags1).isEqualTo(4);


//    Day07.TreeBag lightRed = new Day07.TreeBag("lightRed");
//    Day07.TreeBag brightWhite = new Day07.TreeBag("brightWhite");
//    Day07.TreeBag mutedYellow = new Day07.TreeBag("mutedYellow");
//    lightRed.setContainsLeft(brightWhite, 1);
//    lightRed.setContainsRight(mutedYellow, 2);
//
//    int count = findBagColorCount(lightRed);

//    assertThat(count).isEqualTo(9);

//    assertThat(bags.size()).isEqualTo(9);
//    assertThat(bagsThatContain(bags, "shiny gold bag")).isEqualTo(4);

  }

  //scan whole employee hashMap to form a list of subordinates for the given id
  private static List<HaversackNode> getSubordinatesById(String rid) {
//    System.out.println("rid: " + rid);
    List<HaversackNode> subordinates = null; // new ArrayList<HaversackNode>();

    for (HaversackNode e : haversacks.values()) {
//      System.out.println(e.getColor());
      if (e.getColor().equalsIgnoreCase(rid)) {
        subordinates = e.getSubordinates();
//        return subordinates;
        break;
      }
//      List<HaversackNode> theseSubs = e.getSubordinates();
//      for(HaversackNode ee: theseSubs) {
//        System.out.println(ee.getColor());
//        if (ee.getColor().equalsIgnoreCase(rid)) {
////        if (e.getContainedById().equalsIgnoreCase(rid)) {
//          subordinates.add(e);
//        }
//
//      }
    }
    return subordinates;
  }

  private static HaversackNode getNodeById(String rid) {

    for (HaversackNode e : haversacks.values()) {
      if (e.getColor().equalsIgnoreCase(rid)) {
        return e;
      }
    }
    return null;

  }


  //build tree recursively
  private static void buildHierarchyTree(HaversackNode localRoot) {
    HaversackNode havSack = localRoot;
    List<HaversackNode> subordinates = getSubordinatesById(havSack.getColor());
    if (subordinates == null || subordinates.size() == 0) {
      return;
    }

    havSack.setSubordinates(subordinates);

    for (HaversackNode e : subordinates) {
      buildHierarchyTree(e);
    }
  }


  //build tree recursively
  private static int countBag(String color, int countFind, HaversackNode localNode) {
    HaversackNode havSack = localNode;
    if (havSack == null) {
      return countFind;
    }

//    if (havSack.getColor().equalsIgnoreCase(color)) {
//      System.out.println(">>> debug:countBag::               *** found color add one ");
//      return 1 + countBag(color, countFind, null);
//    }


    List<HaversackNode> subordinates = getSubordinatesById(havSack.getColor());
    if (subordinates == null) {
      return countFind;
    }

    System.out.println("debug:countBag:: this nodes subordinate list length: " + subordinates.size());
    System.out.println("debug:countBag:: " + countFind + " " + havSack.getColor());
//    List<HaversackNode> subordinates = havSack.getSubordinates();
//    havSack.setSubordinates(subordinates);
//    if(havSack.getColor().equalsIgnoreCase(color)) {
//      System.out.println(">>> debug:countBag::               *** found color add one ");
//      return countBag(color, countFind + 1, null);
//    }


    for (HaversackNode e : subordinates) {
      if (e.getColor().equalsIgnoreCase(color)) {
        return 1 + countBag(color, countFind, null); // e);
      } else {
        return countBag(color, countFind, e);
      }

//      if (e.getSubordinates() == null) {
//        return countFind;
//      }

//      for( HaversackNode hn: e.getSubordinates()) {
//        System.out.println("...........................get sub sub ords....  ");
//        return countBag(color, countFind, hn);
//      }
    }
    return countFind;
  }


  private HaversackNode passLineInV2(String line2) {
    System.out.println("passLineInV2");
    String[] things = line2.split("contain");
    String firstPart = things[0];
    String secondPart = things[1];
    String color = Day07.getColor(firstPart);
//    Day07.Haversack haversack = new Day07.Haversack(color);
//    haversack.setHashMap(getOtherBags2(secondPart));

    HaversackNode haversack = new HaversackNode(color, 0, "");
    System.out.println(secondPart);
    haversack.setSubordinates(getOtherBagsV3(color, secondPart));
    return haversack;
  }

  private List<HaversackNode> getOtherBagsV3(String containedBy, String secondPart) {
    System.out.println("getOtherBagsV3 " + containedBy);
    List<HaversackNode> list = new ArrayList<>(8);
    boolean isComma = false;
    String[] bagParts = null;
    if (secondPart.indexOf(',') > 0) {
      System.out.println("found a comma");
      isComma = true;
      bagParts = secondPart.split(",");
      for (String s : bagParts) {
        list.add(extractedV2(containedBy, s));
      }
    } else {
      System.out.println("no comma");
      list.add(extractedV2(containedBy, secondPart));

      secondPart = secondPart.trim();
      if (secondPart.equalsIgnoreCase("no other bags.")) {
        return null;
      }

    }
    return list;
  }


  private Map<String, Integer> getOtherBags2(String secondPart) {
    Map<String, Integer> map = new HashMap<>(8);
    boolean isComma = false;
    String[] bagParts = null;
    if (secondPart.indexOf(',') > 0) {
//      System.out.println("found a comma");
      isComma = true;
      bagParts = secondPart.split(",");
      for (String s : bagParts) {
        Map<String, Integer> sMap = new HashMap<>(extracted(s));

        Map<String, Integer> result = Stream.concat(map.entrySet().stream(), sMap.entrySet().stream())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (value1, value2) -> Integer.valueOf(value1)));
        map = result;
      }
    } else {
//      System.out.println("no comma");
      Map<String, Integer> sMap = new HashMap<>(extracted(secondPart));

      Map<String, Integer> result = Stream.concat(map.entrySet().stream(), sMap.entrySet().stream())
          .collect(Collectors.toMap(
              Map.Entry::getKey,
              Map.Entry::getValue,
              (value1, value2) -> Integer.valueOf(value1)));
      map = result;
    }

//    String[] secondParts = secondPart.split(" ");
    return map;
  }

  private long getNumberBags(String findColor) {
//    Day07.Haversack haversack = new Day07.Haversack(color);
    long countBags1 = haversackSet.stream()
        .filter(h -> h.getHashMap().containsKey(findColor))
        .count();

    return 3;
  }

  class HaversackNode {
    //    int empId;
    String color;
    int count;
    String containedById;
    List<HaversackNode> subordinates;

    public HaversackNode(String color, int count, String rid) {
      try {
//        this.empId = Integer.parseInt(id);
        this.color = color;
        this.count = count;
        this.containedById = rid;
      } catch (Exception e) {
        System.err.println("Exception creating haversack:" + e);
      }
    }

    List<HaversackNode> getSubordinates() {
      return subordinates;
    }

    void setSubordinates(List<HaversackNode> subordinates) {
      this.subordinates = subordinates;
    }

    public String getColor() {
      return color;
    }

    public void setColor(String color) {
      this.color = color;
    }

    public int getCount() {
      return count;
    }

    public void setCount(int count) {
      this.count = count;
    }

    public String getContainedById() {
      return containedById;
    }

    public void setContainedById(String containedById) {
      this.containedById = containedById;
    }

    @Override
    public String toString() {
      return "HaversackNode{" +
          "color='" + color + '\'' +
          ", count=" + count +
          ", containedById='" + containedById + '\'' +
          ", subordinates=" + subordinates +
          '}';
    }
  }

  public class MMM {
    String color;
    Set<String> contains = new HashSet<>(8);

    public MMM(String color) {
      this.color = color;
    }

    public String getColor() {
      return color;
    }

    public void setColor(String color) {
      this.color = color;
    }

    public Set<String> getContains() {
      return contains;
    }

    public void setContains(Set<String> contains) {
      this.contains = contains;
    }
  }

  @Test
  void testSimpleSet() {
    String[] lines =
        {"light red bags contain 1 bright white bag, 2 muted yellow bags.\n",
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags.\n",
            "bright white bags contain 1 shiny gold bag.\n",
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.\n",
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.\n",
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags.\n",
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.\n",
            "faded blue bags contain no other bags.\n",
            "dotted black bags contain no other bags.\n"
        };

    int totalGroups = 0;
    int bagss = 0;
    final ArrayList<String> result = new ArrayList<>();
//    final ArrayList<String> bags = new ArrayList<>();
//    final HashMap<String, ArrayList<HashMap<String, Integer>>> myBags = new HashMap<>(128);
//    ArrayList<Day07.MyBag> bags = new ArrayList<>(128);
//    HashMap<String, Integer> bag;

    for (int i = 0; i < lines.length; i++) {
      String line = lines[i];
//      System.out.println("" + i + ":" + line.trim().length() + " " + line.trim());
      result.add(line);
//      System.out.println(line);
    }

    boolean recordStarted = false;
    int ii = 1;

    recordStarted = true;

    MMM mmm = null;
    for (String line2 : result) {
      System.out.println("" + ii++ + ":" + line2.length() + " " + line2.trim());
      line2 = line2.trim();
      mmm = passLineInV3(line2);
      System.out.println("mmm: " + mmm.getColor());

      Iterator<String> itr = mmm.getContains().iterator();
      while (itr.hasNext()) {
        System.out.println("     >>> " + itr.next());
      }


    }
  }

  private MMM passLineInV3(String line2) {
    System.out.println("passLineInV3");
    String[] things = line2.split("contain");
    String firstPart = things[0];
    String secondPart = things[1];
    String color = Day07.getColor(firstPart);
//    Day07.Haversack haversack = new Day07.Haversack(color);
//    haversack.setHashMap(getOtherBags2(secondPart));

    MMM mmm = new MMM(color);
//    System.out.println(secondPart);
    mmm.setContains(getOtherBagsV4(secondPart));
    return mmm;
  }

  private Set<String> getOtherBagsV4(String secondPart) {
    System.out.println("getOtherBagsV4 ");
    Set<String> set = new HashSet<>(8);
    boolean isComma = false;
    String[] bagParts = null;
    if (secondPart.indexOf(',') > 0) {
      System.out.println("found a comma");
      isComma = true;
      bagParts = secondPart.split(",");
      for (String s : bagParts) {
        set.add(extractedV4(s));
      }
    } else {
      System.out.println("no comma");
      set.add(extractedV4(secondPart));
    }

//    Iterator<String> itr = set.iterator();
//    while(itr.hasNext()){
//      System.out.println(itr.next());
//    }

    return set;
  }

  private String extractedV4(String s) {
    s = s.trim();
    System.out.println("                     :" + s);

    String[] words = s.split(" ");
    String color =
        words[1] + words[2].substring(0, 1).toUpperCase(Locale.ROOT) + words[2].substring(1).toLowerCase(Locale.ROOT);
//    if(words[0].equalsIgnoreCase("no")) words[0] = "0";
//    if(color.equalsIgnoreCase("otherbags.")) color="otherBag";
//    System.out.println(" bag(s): " + words[0] + " " + color);
//    hashMap.put(color, Integer.valueOf(words[0]));
//        System.out.println("The color of bag: " + color);


//    HaversackNode haversackNode = new HaversackNode(color, Integer.valueOf(words[0]), ownerColor);
//    return haversackNode;
    System.out.println(color);
    return color;
  }

  @Test
  void testDay7() {
    String printer = "Solving Advent of Coding Day 7";
    System.out.println(printer);
    // Setup array of passports
    ArrayList<LuggageRule> processingRules = new ArrayList<>();
    Pattern parentPattern = Pattern.compile("^\\w+ \\w+");
    Pattern childPattern = Pattern.compile("\\d+ \\w+ \\w+");
    // Read file and put into list
    try {

      final String filename = "src/main/resources/haversacks.txt";
      File myObj = new File(filename);
      Scanner myReader = new Scanner(myObj);
      int lineCounter = 0;
      while (myReader.hasNextLine()) {
        if (myReader.hasNextLine()) {
          processingRules.add(new LuggageRule());
        }
        String newData = myReader.nextLine();
        // Parent
        Matcher parentMatch = parentPattern.matcher(newData);
        parentMatch.find();
        processingRules.get(lineCounter).parentDescription.descripOne = parentMatch.group().split(" ")[0];
        processingRules.get(lineCounter).parentDescription.descripTwo = parentMatch.group().split(" ")[1];
        // Children
        Matcher childMatch = childPattern.matcher(newData);
        int childNumber = 0;
        while (childMatch.find()) {
          processingRules.get(lineCounter).childDescriptions.add(new BagDescription());
          processingRules.get(lineCounter).childDescriptions.get(childNumber).maxBags = Integer
              .parseInt(childMatch.group().split(" ")[0]);
          processingRules.get(lineCounter).childDescriptions.get(childNumber).descripOne = childMatch.group()
              .split(" ")[1];
          processingRules.get(lineCounter).childDescriptions.get(childNumber).descripTwo = childMatch.group()
              .split(" ")[2];
          childNumber++;
        }
        lineCounter++;
      }
      printer = "Items read " + processingRules.size();
      System.out.println(printer);
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    // Part One
    int canContainOneGoldBag = 0; // Count the number of bags and parent bags that can hold a shiny gold bag
//    ArrayList<LuggageRule> processingRulesCopy1 = processingRules;
    ArrayList<LuggageRule> processingRulesCopy1 = new ArrayList<>(processingRules);
    Queue<BagDescription> queue = new LinkedList<>(); // a target list of bags than can hold gold or parents of gold
    BagDescription goldBag = new BagDescription();
    goldBag.descripOne = "shiny";
    goldBag.descripTwo = "gold";
    queue.add(goldBag); // Add golden ticket to search queue
    while (!queue.isEmpty()) {
      BagDescription currentTargetBag = queue.poll(); // get new search target
      for (int i = 0; i < processingRulesCopy1.size(); i++) {
        for (int j = 0; j < processingRulesCopy1.get(i).childDescriptions.size(); j++) { // search list for
          // target
          if (processingRulesCopy1.get(i).childDescriptions.get(j).descripOne
              .contains(currentTargetBag.descripOne)
              && processingRulesCopy1.get(i).childDescriptions.get(j).descripTwo
              .contains(currentTargetBag.descripTwo)) {
            // add parent to queue
            BagDescription newTargetBag = new BagDescription();
            newTargetBag.descripOne = processingRulesCopy1.get(i).parentDescription.descripOne;
            newTargetBag.descripTwo = processingRulesCopy1.get(i).parentDescription.descripTwo;
            queue.add(newTargetBag);
            processingRulesCopy1.remove(i); // it has already at least one ... so remove it from the queue
            i = 0; // start from top
            canContainOneGoldBag++;
          }

        }
      }
    }
    printer = "Can Contain One Gold Bag: " + canContainOneGoldBag;
    System.out.println(printer);

    // Part Two
    int totalBagCount = 0;
//    ArrayList<LuggageRule> processingRulesCopy2 = processingRules;
    ArrayList<LuggageRule> processingRulesCopy2 = new ArrayList<>(processingRules);
    Queue<BagDescription> queue2 = new LinkedList<>(); // a target list of bags than can hold gold or parents of
    // gold
    queue2.add(goldBag); // Add golden ticket to search queue
    while (!queue2.isEmpty()) {
      // look for parent of golden bag. add childern
      BagDescription currentTargetBag = queue2.poll(); // get new search target
      for (int i = 0; i < processingRulesCopy2.size(); i++) {
        if (processingRulesCopy2.get(i).parentDescription.descripOne.contains(currentTargetBag.descripOne)
            && processingRulesCopy2.get(i).parentDescription.descripTwo
            .contains(currentTargetBag.descripTwo)) {
          // add children to queue
          for (int j = 0; j < processingRulesCopy2.get(i).childDescriptions.size(); j++) {
            for (int x = 0; x < processingRulesCopy2.get(i).childDescriptions.get(j).maxBags; x++) {
              totalBagCount++;
              queue2.add(processingRulesCopy2.get(i).childDescriptions.get(j));
            }
          }
        }
      }
    }

    printer = "Total Bags: " + totalBagCount;
    System.out.println(printer);


  }

  class LuggageRule { // Defines a luggage rule
    BagDescription parentDescription = new BagDescription(); // desciption of the bag
    List<BagDescription> childDescriptions = new ArrayList<>(); // desciptions of the bags that fit inside
  }

  class BagDescription { // defines a piece of luggage and its count
    int maxBags = 0;
    String descripOne = "";
    String descripTwo = "";
  }

}
