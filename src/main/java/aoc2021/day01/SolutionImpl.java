package aoc2021.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class SolutionImpl implements aoc2021.day01.Solution {
    @Override
    public int solvePart1(InputStream input) throws IOException, NumberFormatException {
        return DepthReport.from(input).slidingWindowIncreaseCount(1);
    }

    @Override
    public int solvePart2(InputStream input) throws IOException, NumberFormatException {
        return DepthReport.from(input).slidingWindowIncreaseCount(3);
    }

    private static final class DepthReport {
        final int[] depths;

        DepthReport(int[] depths) {
            this.depths = depths;
        }

        static DepthReport from(InputStream stream) throws NumberFormatException, IOException {
            int[] depths = new BufferedReader(new InputStreamReader(stream)).lines() //
                    .mapToInt(Integer::parseInt) //
                    .toArray();

            return new DepthReport(depths);
        }

        int slidingWindowIncreaseCount(int windowSize) {
            Integer[] depths = IntStream.of(this.depths) //
                    .mapToObj(Integer::valueOf) //
                    .toArray(Integer[]::new);

            Iterator<Integer> sums = new SlidingWindow<>(windowSize, depths) //
                    .iterator(coll -> coll.stream().collect(Collectors.summingInt(Integer::intValue)));

            int previousSum = sums.next();
            int increases = 0;
            while (sums.hasNext()) {
                int sum = sums.next();
                if (sum > previousSum) {
                    increases++;
                }
                previousSum = sum;
            }
            return increases;
        }
    }

    private static final class SlidingWindow<T> {
        final int windowSize;
        final T[] array;

        SlidingWindow(int windowSize, T[] array) {
            this.array = array;
            this.windowSize = windowSize;
        }

        <U> Iterator<U> iterator(Function<Collection<T>, U> reducer) {
            return new Iterator<U>() {
                int cursor = 0;

                @Override
                public boolean hasNext() {
                    return cursor + windowSize <= array.length;
                }

                @Override
                public U next() {
                    List<T> list = new LinkedList<>();
                    for (int i = 0; i < windowSize; i++) {
                        list.add(array[cursor + i]);
                    }
                    cursor++;
                    return reducer.apply(list);
                }
            };
        }
    }
}
