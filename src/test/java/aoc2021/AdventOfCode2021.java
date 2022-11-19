package aoc2021;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.Test;

public class AdventOfCode2021 {
    @Test
    public void day_01_part1_test() throws Exception {
        try (InputStream input = AdventOfCode2021.class.getResourceAsStream("day01.txt")) {
            aoc2021.day01.Solution solution = new aoc2021.day01.SolutionImpl();
            assertEquals(1167, solution.solvePart1(input));
        }
    }

    @Test
    public void day_01_part2_test() throws Exception {
        try (InputStream input = AdventOfCode2021.class.getResourceAsStream("day01.txt")) {
            aoc2021.day01.Solution solution = new aoc2021.day01.SolutionImpl();
            assertEquals(1130, solution.solvePart2(input));
        }
    }
}
