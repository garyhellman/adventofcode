package com.adventofcode.y2020;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

public class Y2020Day1Tests {

    final int SUM2020 = 2020;
    final String filename = "src/test/resources/input.txt";
    final ArrayList<Integer> result = new ArrayList<>();

    @Test
    void readFileintoArray() {
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

            assert(data.trim().contains("1602"));

//            System.out.println(data.trim());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenFilePath_streamFileData() {
        try {
            List<Integer> numbers = Files.lines(Paths.get(filename))
                    .map(line -> line.split("\\s+")).flatMap(Arrays::stream)
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            assert (numbers.size() == 200);
            assert (numbers.get(numbers.size()-1) == 1602);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            assert (numbers.get(numbers.size()-1) == 1602);

        for (Integer item : numbers) {
            Integer i = Integer.valueOf(item);
            for (Integer item2j : numbers) {
                Integer j = Integer.valueOf(item2j);
                for(Integer k : numbers) {
                    if (i+j+k == SUM2020) {
                        System.out.println("********" + i + " : " + j + " : " + k);
                        System.out.println("********" + i * j * k);
                    }
                }
            }
        }

    }

}
