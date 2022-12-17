package day03;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public final class SolutionImpl implements day03.Solution {
    @Override
    public Object solvePart1(InputStream input) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            List<Rucksack> rucksacks = reader.lines() //
                    .map(line -> new Rucksack(line)) //
                    .collect(Collectors.toList());
            int priorities = 0;
            for (Rucksack rucksack : rucksacks) {
                try {
                    rucksack.validate();
                } catch (Rucksack.ItemInBothCompartmentsException e) {
                    priorities += e.priority();
                }
            }
            return priorities;
        }
    }

    @Override
    public Object solvePart2(InputStream input) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            Iterator<Rucksack> rucksacks = reader.lines() //
                    .map(line -> new Rucksack(line)) //
                    .iterator();
            int priorities = 0;
            while (rucksacks.hasNext()) {
                Group group = new Group(rucksacks.next(), rucksacks.next(), rucksacks.next());
                priorities += group.priority();
            }
            return priorities;
        }
    }
}

class Rucksack {
    final String firstCompartment;
    final String secondCompartment;

    Rucksack(String itemList) {
        int compartmentSize = itemList.length() / 2;
        this.firstCompartment = itemList.substring(0, compartmentSize);
        this.secondCompartment = itemList.substring(compartmentSize, 2 * compartmentSize);
    }

    class ItemInBothCompartmentsException extends Exception {
        final char item;

        ItemInBothCompartmentsException(char item) {
            super();
            this.item = item;
        }

        int priority() {
            return 'A' <= item && item <= 'Z' ? item - 'A' + 27 : item - 'a' + 1;
        }
    }

    void validate() throws ItemInBothCompartmentsException {
        for (char c1 : firstCompartment.toCharArray()) {
            if (secondCompartment.contains(Character.toString(c1))) {
                throw new ItemInBothCompartmentsException(c1);
            }
        }
    }
}

class Group {
    final Rucksack first;
    final Rucksack second;
    final Rucksack third;

    Group(Rucksack first, Rucksack second, Rucksack third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    char badgeItem() {
        for (char c1 : (first.firstCompartment + first.secondCompartment).toCharArray()) {
            if ((second.firstCompartment + second.secondCompartment).contains(Character.toString(c1))
                    && (third.firstCompartment + third.secondCompartment).contains(Character.toString(c1))) {
                return c1;
            }
        }
        throw new RuntimeException("No badge in group");
    }

    int priority() {
        char item = badgeItem();
        return 'A' <= item && item <= 'Z' ? item - 'A' + 27 : item - 'a' + 1;
    }
}