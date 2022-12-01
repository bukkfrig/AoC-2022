package aoc2021.day01;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.Test;

public class Tests {
    @Test
    public void part_1() throws Exception {
        try (InputStream input = Tests.class.getResourceAsStream("input.txt")) {
            Solution solution = new SolutionImpl();
            assertEquals(1167, solution.solvePart1(input));
        }
    }

    @Test
    public void part_2() throws Exception {
        try (InputStream input = Tests.class.getResourceAsStream("input.txt")) {
            Solution solution = new SolutionImpl();
            assertEquals(1130, solution.solvePart2(input));
        }
    }
}
