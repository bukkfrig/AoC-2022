package day01;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.Test;

public class Tests {
    @Test
    public void part_1_example_1() throws Exception {
        try (InputStream input = Tests.class.getResourceAsStream("example1.txt")) {
            Solution solution = new SolutionImpl();
            assertEquals(24000, solution.solvePart1(input));
        }
    }
    
    @Test
    public void part_1() throws Exception {
        try (InputStream input = Tests.class.getResourceAsStream("input.txt")) {
            Solution solution = new SolutionImpl();
            assertEquals(72718, solution.solvePart1(input));
        }
    }

    @Test
    public void part_2_example_1() throws Exception {
        try (InputStream input = Tests.class.getResourceAsStream("example1.txt")) {
            Solution solution = new SolutionImpl();
            assertEquals(45000, solution.solvePart2(input));
        }
    }
    
    @Test
    public void part_2() throws Exception {
        try (InputStream input = Tests.class.getResourceAsStream("input.txt")) {
            Solution solution = new SolutionImpl();
            assertEquals(213089, solution.solvePart2(input));
        }
    }
}
