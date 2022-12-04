package day02;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.Test;

public class Tests {
    @Test
    public void part_1_example_1() throws Exception {
        try (InputStream input = Tests.class.getResourceAsStream("example1.txt")) {
            Solution solution = new SolutionImpl();
            assertEquals(15, solution.solvePart1(input));
        }
    }

    @Test
    public void part_1() throws Exception {
        try (InputStream input = Tests.class.getResourceAsStream("input.txt")) {
            Solution solution = new SolutionImpl();
            assertEquals(12458, solution.solvePart1(input));
        }
    }

    @Test
    public void part_2_example_1() throws Exception {
        try (InputStream input = Tests.class.getResourceAsStream("example1.txt")) {
            Solution solution = new SolutionImpl();
            assertEquals(12, solution.solvePart2(input));
        }
    }

    @Test
    public void part_2() throws Exception {
        try (InputStream input = Tests.class.getResourceAsStream("input.txt")) {
            Solution solution = new SolutionImpl();
            assertEquals(12683, solution.solvePart2(input));
        }
    }
}
