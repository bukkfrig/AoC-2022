package day02;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public final class SolutionImpl implements day02.Solution {
    @Override
    public Object solvePart1(InputStream input) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            StrategyGuide guide = new AssumedStrategyGuide(reader.lines().toArray(String[]::new));
            return IntStream.rangeClosed(1, guide.rounds()) //
                    .mapToObj(guide::predict) //
                    .mapToInt(score -> score[1]) //
                    .sum();
        }
    }

    @Override
    public Object solvePart2(InputStream input) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            StrategyGuide guide = new ActualStrategyGuide(reader.lines().toArray(String[]::new));
            return IntStream.rangeClosed(1, guide.rounds()) //
                    .mapToObj(guide::predict) //
                    .mapToInt(score -> score[1]) //
                    .sum();
        }
    }

    static class RockPaperScissors {
        static enum Throw {
            ROCK(1), PAPER(2), SCISSORS(3);

            int points;

            Throw(int points) {
                this.points = points;
            }
        }

        static final int[] WIN = { 6, 0 };
        static final int[] LOSS = { 0, 6 };
        static final int[] DRAW = { 3, 3 };

        static private int[] baseScore(Throw p1, Throw p2) {
            switch (p1) {
            case ROCK:
                switch (p2) {
                case ROCK:
                    return DRAW;
                case PAPER:
                    return LOSS;
                case SCISSORS:
                    return WIN;
                }
            case PAPER:
                switch (p2) {
                case ROCK:
                    return WIN;
                case PAPER:
                    return DRAW;
                case SCISSORS:
                    return LOSS;
                }
            case SCISSORS:
                switch (p2) {
                case ROCK:
                    return LOSS;
                case PAPER:
                    return WIN;
                case SCISSORS:
                    return DRAW;
                }
            }
            throw new IllegalArgumentException();
        }

        static int[] score(Throw p1, Throw p2) {
            int[] baseScore = baseScore(p1, p2);
            return new int[] { baseScore[0] + p1.points, baseScore[1] + p2.points };
        }
    }

    static interface StrategyGuide {
        public int[] predict(int round);

        public int rounds();
    }

    static class AssumedStrategyGuide implements StrategyGuide {
        protected final String[] rows;

        AssumedStrategyGuide(String[] rows) {
            this.rows = rows;
        }

        @Override
        public int rounds() {
            return rows.length;
        }

        @Override
        public int[] predict(int round) {
            RockPaperScissors.Throw p1 = throwForPlayerOne(round);
            RockPaperScissors.Throw p2 = throwForPlayerTwo(round);
            return RockPaperScissors.score(p1, p2);
        }
        
        protected RockPaperScissors.Throw throwForPlayerOne(int round) {
            int rowIndex = round - 1;
            String encrypted = rows[rowIndex].split("\\ ")[0];
            return throwForPlayerOne(encrypted);
        }
        
        protected RockPaperScissors.Throw throwForPlayerTwo(int round) {
            int rowIndex = round - 1;
            String encrypted = rows[rowIndex].split("\\ ")[1];
            return throwForPlayerTwo(encrypted);
        }

        private static RockPaperScissors.Throw throwForPlayerOne(String encrypted) {
            if (encrypted.equals("A")) {
                return RockPaperScissors.Throw.ROCK;
            } else if (encrypted.equals("B")) {
                return RockPaperScissors.Throw.PAPER;
            } else if (encrypted.equals("C")) {
                return RockPaperScissors.Throw.SCISSORS;
            }
            throw new IllegalArgumentException("Invalid encrypted value for Player 1: " + encrypted);
        }

        private static RockPaperScissors.Throw throwForPlayerTwo(String encrypted) {
            if (encrypted.equals("X")) {
                return RockPaperScissors.Throw.ROCK;
            } else if (encrypted.equals("Y")) {
                return RockPaperScissors.Throw.PAPER;
            } else if (encrypted.equals("Z")) {
                return RockPaperScissors.Throw.SCISSORS;
            }
            throw new IllegalArgumentException("Invalid encrypted value for Player 2: " + encrypted);
        }
    }

    static final class ActualStrategyGuide extends AssumedStrategyGuide {
        ActualStrategyGuide(String[] rows) {
            super(rows);
        }

        @Override
        protected RockPaperScissors.Throw throwForPlayerTwo(int round) {
            int rowIndex = round - 1;
            String encrypted = rows[rowIndex].split("\\ ")[1];
            RockPaperScissors.Throw p1 = throwForPlayerOne(round);
            return throwForPlayerTwo(encrypted, p1);
        }
        
        private static RockPaperScissors.Throw throwForPlayerTwo(String encrypted, RockPaperScissors.Throw other) {
            if (encrypted.equals("X")) {
                return toLoss(other);
            } else if (encrypted.equals("Y")) {
                return other;
            } else if (encrypted.equals("Z")) {
                return toWin(other);
            }
            throw new IllegalArgumentException("Invalid encrypted value for Player 2: " + encrypted);
        }

        private static RockPaperScissors.Throw toLoss(RockPaperScissors.Throw other) {
            switch (other) {
            case ROCK:
                return RockPaperScissors.Throw.SCISSORS;
            case PAPER:
                return RockPaperScissors.Throw.ROCK;
            case SCISSORS:
                return RockPaperScissors.Throw.PAPER;
            }
            throw new IllegalArgumentException();
        }

        private static RockPaperScissors.Throw toWin(RockPaperScissors.Throw other) {
            switch (other) {
            case ROCK:
                return RockPaperScissors.Throw.PAPER;
            case PAPER:
                return RockPaperScissors.Throw.SCISSORS;
            case SCISSORS:
                return RockPaperScissors.Throw.ROCK;
            }
            throw new IllegalArgumentException();
        }
    }
}
