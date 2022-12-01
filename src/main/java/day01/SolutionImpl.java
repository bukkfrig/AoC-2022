package day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class SolutionImpl implements day01.Solution {
    @Override
    public Object solvePart1(InputStream input) throws Exception {
        return parseElves(input).stream() // .
                .max(Comparator.comparing(Elf::caloriesInFoodstuffs)) //
                .get() //
                .caloriesInFoodstuffs();
    }

    @Override
    public Object solvePart2(InputStream input) throws Exception {
        return parseElves(input).stream() //
                .sorted(Comparator.comparing(Elf::caloriesInFoodstuffs).reversed()) //
                .limit(3) //
                .mapToInt(Elf::caloriesInFoodstuffs) //
                .sum();
    }

    static List<Elf> parseElves(InputStream input) throws IOException {
        List<Elf> elves = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            Iterator<String> lines = reader.lines().iterator();
            while (lines.hasNext()) {
                elves.add(parseElf(lines));
            }
        }
        return elves;
    }

    static Elf parseElf(Iterator<String> input) {
        List<Foodstuff> foodstuffs = new LinkedList<>();
        while (input.hasNext()) {
            String line = input.next();
            if (line.isEmpty()) {
                return new Elf(foodstuffs);
            }
            foodstuffs.add(new Foodstuff(Integer.valueOf(line)));
        }
        return new Elf(foodstuffs);
    }
}
