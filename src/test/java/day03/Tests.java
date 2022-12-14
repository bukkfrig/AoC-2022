package day03;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.Test;

public class Tests {

    @Test
    public void part_1_example_1() throws Exception {
        try (InputStream input = Tests.class.getResourceAsStream("example1.txt")) {
            Solution solution = new SolutionImpl();
            assertEquals(157, solution.solvePart1(input));
        }
    }

    @Test
    public void part_1() throws Exception {
        try (InputStream input = Tests.class.getResourceAsStream("input.txt")) {
            Solution solution = new SolutionImpl();
            assertEquals(7811, solution.solvePart1(input));
        }
    }

    @Test
    public void part_2_example_1() throws Exception {
        try (InputStream input = Tests.class.getResourceAsStream("example1.txt")) {
            Solution solution = new SolutionImpl();
            assertEquals(70, solution.solvePart2(input));
        }
    }

    @Test
    public void part_2() throws Exception {
        try (InputStream input = Tests.class.getResourceAsStream("input.txt")) {
            Solution solution = new SolutionImpl();
            assertEquals(2639, solution.solvePart2(input));
        }
    }
}
