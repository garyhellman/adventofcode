package com.adventofcode.y2020;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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


public class Y2020Day02Tests {

    final int SUM2020 = 2020;
    final String filename = "src/test/resources/passwords.txt";
    final ArrayList<Integer> result = new ArrayList<>();

    @Test
    public void whenReadWithStreamTokenizer_thenCorrectTokens()
            throws IOException {
        String file = "src/test/resources/passwords.txt";
        FileReader reader = new FileReader(file);
        StreamTokenizer tokenizer = new StreamTokenizer(reader);

        // token 1
        tokenizer.nextToken();
        System.out.println(tokenizer.toString());
        System.out.println(tokenizer.ttype);
        System.out.println(tokenizer.sval);

//        assertEquals(StreamTokenizer.TT_WORD, tokenizer.ttype);
//        assertEquals("Hello", tokenizer.sval);

        // token 2
        tokenizer.nextToken();
        System.out.println(tokenizer.toString());
        System.out.println(tokenizer.ttype);
        System.out.println(tokenizer.sval);
//        assertEquals(StreamTokenizer.TT_NUMBER, tokenizer.ttype);
//        assertEquals(1, tokenizer.nval, 0.0000001);

        // token 3
        tokenizer.nextToken();
        System.out.println(tokenizer.toString());
        System.out.println(tokenizer.ttype);
        System.out.println(tokenizer.sval);


//        assertEquals(StreamTokenizer.TT_EOF, tokenizer.ttype);
        reader.close();
    }

    //    @Disabled
    @Test
    void readFileintoArray() {
        final ArrayList<String> result = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while (br.ready()) {
                result.add(br.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert (result.size() == 1000);
        String policy = null;
        String pw = null;
        int numberValid = 0;
        int numberValid2 = 0;
        for (String line : result) {
//            System.out.println(line);
            String[] tokens = line.split(":");
//            for (String t : tokens) System.out.println(t);
            if (tokens.length == 2) {
                policy = tokens[0];
                pw = tokens[1];
            }
//            System.out.println("policy: " + policy + " pw: " + pw);
            if(isValid(policy, pw)) {
                System.out.println(pw + " isValid " + policy );
                numberValid++;
            }

            if(isValid2(policy, pw)) {
                System.out.println(pw + " isValid2 " + policy );
                numberValid2++;
            }
        }
        System.out.println("Number of valid passwords: " + numberValid);
        System.out.println("Number of valid 2 passwords: " + numberValid2);
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
        String firstChar = pw.substring(firstPos(range)-1, firstPos(range));
        String secondChar = pw.substring(secondPos(range)-1, secondPos(range));
//        System.out.println(firstChar + " " + secondChar);
        if(firstChar.equalsIgnoreCase(c) ^ secondChar.equalsIgnoreCase(c)) {
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

        if(count >= lowerRange && count <= upperRange) return true;
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
        for (int i=0; i<pw.length(); i++) {
            if(pw.substring(i,i+1).equalsIgnoreCase(c)) {
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
        assert(isValid2("1-3 a", "abcde"));
        assert(!isValid2("1-3 b", "cdefg"));
        assert(!isValid2("1-3 c", "ccccccccc"));
        assert(!isValid2("5-6 s", "zssmssbsms"));
        assert(!isValid2("5-6 s", "   zssmssbsms   "));
    }

    @Test
    void testInRange() {
        assert(inRange("1-3", 1));
        assert(inRange("1-3", 2));
        assert(inRange("1-3", 3));
        assert(!inRange("1-3", 4));
    }


    @Disabled
    @Test
    void readFileintoArray2() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while (br.ready()) {
                result.add(Integer.valueOf(br.readLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert (result.size() == 200);
    }

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

    @Disabled
    @Test
    public void givenFilePath_streamFileData2() {
        List<Integer> numbs = null;
        try {
            numbs = Files.lines(Paths.get(filename))
                    .map(line -> line.split("\\s+")).flatMap(Arrays::stream)
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Integer> numbers = new ArrayList<>(numbs);
        assert (numbers.size() == 200);
        assert (numbers.get(numbers.size() - 1) == 1602);

        for (Integer item : numbers) {
            Integer i = Integer.valueOf(item);
            for (Integer item2j : numbers) {
                Integer j = Integer.valueOf(item2j);
                for (Integer k : numbers) {
                    if (i + j + k == SUM2020) {
                        System.out.println("********" + i + " : " + j + " : " + k);
                        System.out.println("********" + i * j * k);
                    }
                }
            }
        }

    }


}

