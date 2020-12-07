package com.adventofcode.y2020;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toSet;

@AllArgsConstructor
public class Day5Simplified {

  private static final String ONE = "1";
  private static final String ZERO = "0";

  private static final String ONE_MAPPED_LETTER_REGEX = "[BR]";
  private static final String ZERO_MAPPED_LETTER_REGEX = "[FL]";

  private static final int BINARY_RADIX = 2;

  List<String> boardingPasses;

  public int part1() {
    return maximumSeatId();
  }

  private int maximumSeatId() {
    return existingSeatIdStream().max().getAsInt();
  }

  private IntStream existingSeatIdStream() {
    return boardingPasses.stream().mapToInt(this::calculateSeatId);
  }

  private int calculateSeatId(String boardingPass) {
    String boardingPassInBinary = boardingPass
        .replaceAll(ONE_MAPPED_LETTER_REGEX, ONE)
        .replaceAll(ZERO_MAPPED_LETTER_REGEX, ZERO);
    return Integer.parseInt(boardingPassInBinary, BINARY_RADIX);
  }

  public int part2() {
    Set<Integer> existingSeatIds = existingSeatIds();

    return IntStream.range(minimumSeatId(), maximumSeatId())
        .boxed()
        .filter(not(existingSeatIds::contains))
        .findAny()
        .orElseThrow();
  }

  private Set<Integer> existingSeatIds() {
    return existingSeatIdStream().boxed().collect(toSet());
  }

  private int minimumSeatId() {
    return existingSeatIdStream().min().getAsInt();
  }
}
