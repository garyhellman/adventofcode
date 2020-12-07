package com.adventofcode.y2020;

import org.javatuples.Pair;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
//import com.flipkart.utils

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.adventofcode.y2020.Y2020ApplicationDay03.getNumTrees;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

// To try to debug the problem, they have created a list (your puzzle input) of passwords (according to the corrupted database) and the corporate policy when that password was set.
//
//For example, suppose you have the following list:
//
//1-3 a: abcde
//1-3 b: cdefg
//2-9 c: ccccccccc
//Each line gives the password policy and then the password.
// The password policy indicates the lowest and highest number of times a given letter must appear for
// the password to be valid. For example, 1-3 a means that the password must contain a at least 1 time
// and at most 3 times.
//
//In the above example, 2 passwords are valid. The middle password, cdefg, is not; it contains no instances of b,
// but needs at least 1. The first and third passwords are valid: they contain one a or nine c, both within the
// limits of their respective policies.
//
//How many passwords are valid according to their policies?


public class Y2020Day03Tests {

    final String filename = "src/test/resources/trees.txt";


    //    @Disabled
    @Test
    void readFileintoArray() {


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
                StringBuffer sb = new StringBuffer(320);
                int y = 3 * 325 / line.length();
                for (int i = 0; i <= y; i++) {
                    sb.append(line);
                }
//                result.add(br.readLine());
                result.add(sb.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        assert (result.size() == 323);
//        assert (result.get(0).length() == 31);
        Pair<Integer, Integer> limit = new Pair<>(result.get(0).length(), result.size());

//        int ii = 1;
//        for (String line : result) {
//            System.out.println("" + ii++ + ":" + line.length() + " " + line);
//        }

        String isItaTree = null;
//        String s = getTreeOrOpen(currentPos, result);
//        assertThat(s).isEqualTo(OPEN);

//        Pair<Integer, Integer> xxx = new Pair<>(4, 1);
//        assertThat(xxx).isEqualTo(getNewPosition(currentPos, slope), result);
//        Integer curX = (Integer) currentPos.getValue(XVALUE);
//        Integer curY = (Integer) currentPos.getValue(YVALUE);

        Pair<Integer, Integer> currentPos = new Pair<>(0, 0);

        numTrees1 = getNumTrees(slope1, result, limit, currentPos);
        numTrees2 = getNumTrees(slope2, result, limit, currentPos);
//        numTrees3 = getNumTrees(slope3, result, limit, currentPos);
//        numTrees4 = getNumTrees(slope4, result, limit, currentPos);
//        numTrees5 = getNumTrees(slope5, result, limit, currentPos);

//        currentPos = new Pair<>(0, 0);
//        while(positionWithinLimits(currentPos, limit)) {
//            isItaTree = getTreeOrOpen(currentPos, result);
//            if(isItaTree.equalsIgnoreCase(TREE)) {
////                System.out.println("TREE");
//                numTrees2++;
//            }
//            currentPos = getNewPosition(currentPos, slope2);
//        }
//
//        currentPos = new Pair<>(0, 0);
//        while(positionWithinLimits(currentPos, limit)) {
//            isItaTree = getTreeOrOpen(currentPos, result);
//            if(isItaTree.equalsIgnoreCase(TREE)) {
////                System.out.println("TREE");
//                numTrees3++;
//            }
//            currentPos = getNewPosition(currentPos, slope3);
//        }
//
//        currentPos = new Pair<>(0, 0);
//        while(positionWithinLimits(currentPos, limit)) {
//            isItaTree = getTreeOrOpen(currentPos, result);
//            if(isItaTree.equalsIgnoreCase(TREE)) {
////                System.out.println("TREE");
//                numTrees4++;
//            }
//            currentPos = getNewPosition(currentPos, slope4);
//        }
//
//        currentPos = new Pair<>(0, 0);
//        while(positionWithinLimits(currentPos, limit)) {
//            isItaTree = getTreeOrOpen(currentPos, result);
//            if(isItaTree.equalsIgnoreCase(TREE)) {
//                System.out.println("TREE");
//                numTrees5++;
//            }
//            currentPos = getNewPosition(currentPos, slope5);
//        }

        System.out.println("Number of Trees: " + numTrees1);
        System.out.println("Number of Trees: " + numTrees2);
        System.out.println("Number of Trees: " + numTrees3);
        System.out.println("Number of Trees: " + numTrees4);
        System.out.println("Number of Trees: " + numTrees5);

        System.out.println("Number of Trees: " + numTrees1 * numTrees2 * numTrees3 * numTrees4 * numTrees5);
//        s = getTreeOrOpen(getNewPosition(currentPos, slope), result);
//        assertThat(s).isEqualTo(TREE);
//        s = getTreeOrOpen(getNewPosition(currentPos, slope), result);
//        assertThat(s).isEqualTo(OPEN);

    }

    private boolean isValid(String policy, String pw) {
//        boolean retVal = false;
        String[] tokens = policy.split(" ");
        String range = tokens[0];
//        System.out.println(range);
        String c = tokens[1];
//        System.out.println(c);
        int count = findCount(c, pw);
        return inRange(range, count);
//        return retVal;
    }

    private boolean isValid2(String policy, String inPw) {
        String pw = inPw.trim();
//        System.out.println(policy + " " + pw);
//        boolean retVal = false;
        String[] tokens = policy.trim().split(" ");
        String range = tokens[0];
//        System.out.println(range);
        String c = tokens[1];
//        System.out.println(c);
//        System.out.println(firstPos(range));
//        System.out.println(pw.substring(firstPos(range)-1, firstPos(range)));
//        System.out.println(pw.substring(secondPos(range)-1, secondPos(range)));
        String firstChar = pw.substring(firstPos(range) - 1, firstPos(range));
        String secondChar = pw.substring(secondPos(range) - 1, secondPos(range));
//        System.out.println(firstChar + " " + secondChar);
        if (firstChar.equalsIgnoreCase(c) ^ secondChar.equalsIgnoreCase(c)) {
//            System.out.println(firstChar + " : " + secondChar + " : " + c);
            return true;
        }
//        System.out.println("not valid: " + firstChar + " : " + secondChar + " : " + c);
        return false;
//        return retVal;
    }

    private boolean inRange(String range, int count) {
        System.out.println(range + " : " + count);
        String[] tokens = range.split("-");
        Integer lowerRange = Integer.valueOf(tokens[0]);
        Integer upperRange = Integer.valueOf(tokens[1]);

        if (count >= lowerRange && count <= upperRange) return true;
        return false;
    }

    private int firstPos(String range) {
        String[] tokens = range.split("-");
        Integer lowerRange = Integer.valueOf(tokens[0]);
        Integer upperRange = Integer.valueOf(tokens[1]);
        return lowerRange;
    }

    private int secondPos(String range) {
        String[] tokens = range.split("-");
        Integer lowerRange = Integer.valueOf(tokens[0]);
        Integer upperRange = Integer.valueOf(tokens[1]);
        return upperRange;
    }

    private int findCount(String c, String pw) {
        int count = 0;
        for (int i = 0; i < pw.length(); i++) {
            if (pw.substring(i, i + 1).equalsIgnoreCase(c)) {
                count++;
            }
        }
        return count;
    }

    @Test
    void testFindCount() {
        String abc = "abcabcabc";
        assertEquals(findCount("a", abc), 3, "Did this work?");
    }

    @Test
    void testIsValid() {
        String abc = "abcabcabc";
        assertEquals(findCount("a", abc), 3, "Did this work?");
    }

    @Test
    void testIsValid2() {
        assert (isValid2("1-3 a", "abcde"));
        assert (!isValid2("1-3 b", "cdefg"));
        assert (!isValid2("1-3 c", "ccccccccc"));
        assert (!isValid2("5-6 s", "zssmssbsms"));
        assert (!isValid2("5-6 s", "   zssmssbsms   "));
    }

    @Test
    void testInRange() {
        assert (inRange("1-3", 1));
        assert (inRange("1-3", 2));
        assert (inRange("1-3", 3));
        assert (!inRange("1-3", 4));
    }


//    @Disabled
//    @Test
//    void readFileintoArray2() {
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(filename));
//            while (br.ready()) {
//                result.add(Integer.valueOf(br.readLine()));
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        assert (result.size() == 200);
//    }

    // List<String> result = Files.readAllLines(Paths.get(filename));
    // List<String> result = Files.readAllLines(Paths.get(filename), charset);
    @Disabled
    @Test
    void readAllLinesFileintoArray() {
        List<Integer> numbers = new ArrayList<>();
        Charset charset = Charset.forName("ISO-8859-1");
        try {
            for (String line : Files.readAllLines(Paths.get(filename), charset)) {
                for (String part : line.split("\\s+")) {
                    Integer i = Integer.valueOf(part);
                    numbers.add(i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert (numbers.size() == 200);
    }

    @Disabled
    @Test
    public void givenFilePath_whenUsingFilesLines_thenFileData() {
//        String expectedData = "Hello, world!";

        Path path = null;
        try {
            path = Paths.get(getClass().getClassLoader()
                    .getResource("input.txt").toURI());
            Stream<String> lines = null;
            lines = Files.lines(path);
            String data = lines.collect(Collectors.joining("\n"));
            lines.close();

            assert (data.trim().contains("1602"));

//            System.out.println(data.trim());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Disabled
    @Test
    public void givenFilePath_streamFileData() {
        try {
            List<Integer> numbers = Files.lines(Paths.get(filename))
                    .map(line -> line.split("\\s+")).flatMap(Arrays::stream)
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            assert (numbers.size() == 200);
            assert (numbers.get(numbers.size() - 1) == 1602);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    void testTheirData() {
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

        String[] lines =
                {       "..##.........##.........##.........##.........##.........##.......",
                        "#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..",
                        ".#....#..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.",
                        "..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#",
                        ".#...##..#..#...##..#..#...##..#..#...##..#..#...##..#..#...##..#.",
                        "..#.##.......#.##.......#.##.......#.##.......#.##.......#.##.....",
                        ".#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#",
                        ".#........#.#........#.#........#.#........#.#........#.#........#",
                        "#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...",
                        "#...##....##...##....##...##....##...##....##...##....##...##....#",
                        ".#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#"};

        final ArrayList<String> result = new ArrayList<>();
        for(int i=0;i<lines.length;i++) {
            String line = lines[i];
            StringBuffer sb = new StringBuffer(320);
            int y = 3 * 325 / line.length();
            for (int ii = 0; ii <= y; ii++) {
                sb.append(line);
            }
            result.add(sb.toString());

        }

        Pair<Integer, Integer> limit = new Pair<>(result.get(0).length(), result.size());
//        int ii = 1;
//        for (String myLine : result) {
//            System.out.println("" + ii++ + ":" + myLine.length() + " " + myLine);
//        }


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

        System.out.println("Number of Trees: " + numTrees1 * numTrees2 * numTrees3 * numTrees4 * numTrees5);

    }


}

