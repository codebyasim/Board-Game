package game.basic;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        runGame1();
        runGame2();
        runGame3();
        runGame4();
    }

    // ---------------- GAME 1 ----------------
    public static void runGame1() {

        Red red = new Red();
        Blue blue = new Blue();

        final int[] shakes = new int[]{12, 10, 12};
        int turns = 0;
        int redTurns = 0;
        int blueTurns = 0;

        //Header
        System.out.println("Board: Small, Players: 2, Exact End or overshoot apply , fixed dice, no HITS , no Teleport, no Wormholes");
        System.out.println("Fixed sequence of dice rolls {12,10,12}");
        System.out.println("Board: rows=5 columns=5");
        System.out.println("Red Home (Position 1)");
        System.out.println("Blue Home (Position 25)");
        System.out.println("Game State: Ready → InPlay");

        while (!red.isAtEnd() && !blue.isAtEnd()) {
            int shake = shakes[turns++];

            if (turns % 2 == 1) {
                redTurns++;
                System.out.printf("Red turn %d rolls %d%n", redTurns, shake);

                int start = red.getPosition();
                red.advance(shake);

                if (red.isAtEnd()) {
                    System.out.printf("Red moves from %d to End (Position %d)%n",
                            start, red.getPosition());
                } else {
                    System.out.printf("Red moves from %d to %d%n",
                            start, red.getPosition());
                }

            } else {
                blueTurns++;
                System.out.printf("Blue turn %d rolls %d%n", blueTurns, shake);

                int start = blue.getPosition();
                blue.advance(shake);

                if (blue.isAtEnd()) {
                    System.out.printf("Blue moves from %d to End (Position %d)%n",
                            start, blue.getPosition());
                } else {
                    System.out.printf("Blue moves from %d to %d%n",
                            start, blue.getPosition());
                }
            }
        }

        if (red.isAtEnd()) {
            System.out.printf("Red wins in %d turns.%n", redTurns);
        } else {
            System.out.printf("Blue wins in %d turns.%n", blueTurns);
        }

        System.out.printf("Total turns: %d%n", turns);
        System.out.println("Game State: InPlay → GameOver");
        System.out.println("Dice rolls: {12, 10, 12}");
        System.out.println("Game Id: 1 saved.\n");

    }

    public static void runGame2() {

        Red red = new Red();
        Blue blue = new Blue();
        Random dice = new Random();

        List<Integer> rolls = new ArrayList<>();

        int turns = 0;
        int redTurns = 0;
        int blueTurns = 0;

        // Header
        System.out.println("Board: Small, Players: 2, Exact End or overshoot apply, single Random dice, Apply HIT rule, no Teleport, no Wormholes");
        System.out.println("Single Random 6 sided dice");
        System.out.println("Board: rows=5 columns=5");
        System.out.println("Red Home (Position 1)");
        System.out.println("Blue Home (Position 25)");
        System.out.println("Game State: Ready → InPlay");

        while (!red.isAtEnd() && !blue.isAtEnd()) {

            int shake = dice.nextInt(6) + 1;
            rolls.add(shake);
            turns++;

            if (turns % 2 == 1) {
                redTurns++;
                System.out.printf("Red turn %d rolls %d%n", redTurns, shake);

                int start = red.getPosition();
                int nextIndex = red.getIndex() + shake;

                if (nextIndex > 24) nextIndex = 24;

                // HIT check (not at END)
                if (nextIndex != 24 && blue.getPosition() == red.peekPosition(nextIndex)) {
                    System.out.printf("Red moves from %d to %d 💥 (HIT → move forfeited)%n",
                            start, red.getPosition());
                } else {
                    red.advance(shake);

                    if (red.isAtEnd()) {
                        System.out.printf("Red moves from %d to End (Position %d)%n",
                                start, red.getPosition());
                    } else {
                        System.out.printf("Red moves from %d to %d%n",
                                start, red.getPosition());
                    }
                }

            } else {
                blueTurns++;
                System.out.printf("Blue turn %d rolls %d%n", blueTurns, shake);

                int start = blue.getPosition();
                int nextIndex = blue.getIndex() + shake;

                if (nextIndex > 24) nextIndex = 24;

                if (nextIndex != 24 && red.getPosition() == blue.peekPosition(nextIndex)) {
                    System.out.printf("Blue moves from %d to %d 💥 (HIT → move forfeited)%n",
                            start, blue.getPosition());
                } else {
                    blue.advance(shake);

                    if (blue.isAtEnd()) {
                        System.out.printf("Blue moves from %d to End (Position %d)%n",
                                start, blue.getPosition());
                    } else {
                        System.out.printf("Blue moves from %d to %d%n",
                                start, blue.getPosition());
                    }
                }
            }
        }

        if (red.isAtEnd()) {
            System.out.printf("Red wins in %d turns.%n", redTurns);
        } else {
            System.out.printf("Blue wins in %d turns.%n", blueTurns);
        }

        System.out.printf("Total turns: %d%n", turns);
        System.out.println("Game State: InPlay → GameOver");
        System.out.println("Dice rolls: " + rolls);
        System.out.println("Game Id: 2 saved.");
    }

    public static void runGame3() {

        Red red = new Red();
        Blue blue = new Blue();
        Random dice = new Random();

        List<Integer> rolls = new ArrayList<>();
        Set<Integer> wormholes = Set.of(4, 9, 19, 23);

        int turns = 0;
        int redTurns = 0;
        int blueTurns = 0;

        System.out.println("Board: Small, Players: 2, Exact End + Bounce Back, Random single dice, HIT rule, Wormholes 4:9, 19:23 (no teleport)");
        System.out.println("Board: rows=5 columns=5");
        System.out.println("Red Home (Position 1)");
        System.out.println("Blue Home (Position 25)");
        System.out.println("Game State: Ready → InPlay");

        while (!red.isAtEnd() && !blue.isAtEnd()) {

            int roll = dice.nextInt(6) + 1;
            rolls.add(roll);
            turns++;

            // ---------------- RED ----------------
            if (turns % 2 == 1) {
                redTurns++;
                System.out.printf("Red turn %d rolls %d%n", redTurns, roll);

                int start = red.getPosition();
                int rawIndex = red.getIndex() + roll;

                boolean overshoot = rawIndex > 24;

                int nextIndex = rawIndex;
                if (rawIndex > 24) {
                    int overflow = rawIndex - 24;
                    nextIndex = 24 - overflow;
                }

                if (nextIndex != 24 && blue.getPosition() == red.peekPosition(nextIndex)) {
                    System.out.printf("Red moves from %d to %d 💥 (HIT → move forfeited)%n",
                            start, red.getPosition());
                } else {
                    red.advance(roll);

                    int pos = red.getPosition();

                    if (overshoot) {
                        System.out.printf("Red overshoots END and bounces back to Position %d%s%n",
                                pos, wormholes.contains(pos) ? " 🌀 (WORMHOLE)" : "");
                    } else if (red.isAtEnd()) {
                        System.out.printf("Red moves from %d to End (Position %d)%n",
                                start, pos);
                    } else {
                        System.out.printf("Red moves from %d to %d%s%n",
                                start, pos, wormholes.contains(pos) ? " 🌀 (WORMHOLE)" : "");
                    }
                }

            }
            // ---------------- BLUE ----------------
            else {
                blueTurns++;
                System.out.printf("Blue turn %d rolls %d%n", blueTurns, roll);

                int start = blue.getPosition();
                int rawIndex = blue.getIndex() + roll;

                boolean overshoot = rawIndex > 24;

                int nextIndex = rawIndex;
                if (rawIndex > 24) {
                    int overflow = rawIndex - 24;
                    nextIndex = 24 - overflow;
                }

                if (nextIndex != 24 && red.getPosition() == blue.peekPosition(nextIndex)) {
                    System.out.printf("Blue moves from %d to %d 💥 (HIT → move forfeited)%n",
                            start, blue.getPosition());
                } else {
                    blue.advance(roll);

                    int pos = blue.getPosition();

                    if (overshoot) {
                        System.out.printf("Blue overshoots END and bounces back to Position %d%s%n",
                                pos, wormholes.contains(pos) ? " 🌀 (WORMHOLE)" : "");
                    } else if (blue.isAtEnd()) {
                        System.out.printf("Blue moves from %d to End (Position %d)%n",
                                start, pos);
                    } else {
                        System.out.printf("Blue moves from %d to %d%s%n",
                                start, pos, wormholes.contains(pos) ? " 🌀 (WORMHOLE)" : "");
                    }
                }
            }
        }

        if (red.isAtEnd()) {
            System.out.printf("Red wins in %d turns.%n", redTurns);
        } else {
            System.out.printf("Blue wins in %d turns.%n", blueTurns);
        }

        System.out.printf("Total turns: %d%n", turns);
        System.out.println("Game State: InPlay → GameOver");
        System.out.println("Dice rolls: " + rolls);
        System.out.println("Game Id: 3 saved.");
    }

    public static void runGame4() {

        Red red = new Red();
        Blue blue = new Blue();
        Random dice = new Random();

        List<Integer> rolls = new ArrayList<>();

        // wormhole pairs
        Map<Integer, Integer> wormholes = Map.of(
                4, 9,
                9, 4,
                19, 23,
                23, 19
        );

        int turns = 0;
        int redTurns = 0;
        int blueTurns = 0;

        System.out.println("Board: Small, Players: 2, Exact End + Bounce Back, Random single dice, HIT rule aply, Wormholes 4;9, 19:23 (TELEPORT Exist)");
        System.out.println("Board: rows=5 columns=5");
        System.out.println("Red Home (Position 1)");
        System.out.println("Blue Home (Position 25)");
        System.out.println("Game State: Ready → InPlay");

        while (!red.isAtEnd() && !blue.isAtEnd()) {

            int roll = dice.nextInt(6) + 1;
            rolls.add(roll);
            turns++;

            // -------- RED --------
            if (turns % 2 == 1) {
                redTurns++;
                System.out.printf("Red turn %d rolls %d%n", redTurns, roll);

                int start = red.getPosition();
                int rawIndex = red.getIndex() + roll;

                boolean overshoot = rawIndex > 24;

                int nextIndex = rawIndex;
                if (rawIndex > 24) {
                    int overflow = rawIndex - 24;
                    nextIndex = 24 - overflow;
                }

                int nextPos = red.peekPosition(nextIndex);

                if (nextIndex != 24 && blue.getPosition() == nextPos) {
                    System.out.printf("Red moves from %d to %d 💥 (HIT → move forfeited)%n",
                            start, start);
                } else {
                    red.advance(roll);
                    int pos = red.getPosition();

                    // TELEPORT
                    if (wormholes.containsKey(pos)) {
                        int exit = wormholes.get(pos);
                        red.setPosition(exit);
                        System.out.printf("Red moves from %d to %d 🌀 (TELEPORT to %d)%n",
                                start, pos, exit);
                    } else if (overshoot) {
                        System.out.printf("Red overshoots END and bounces back to Position %d%n", pos);
                    } else if (red.isAtEnd()) {
                        System.out.printf("Red moves from %d to End (Position %d)%n", start, pos);
                    } else {
                        System.out.printf("Red moves from %d to %d%n", start, pos);
                    }
                }

            } else {
                // -------- BLUE --------
                blueTurns++;
                System.out.printf("Blue turn %d rolls %d%n", blueTurns, roll);

                int start = blue.getPosition();
                int rawIndex = blue.getIndex() + roll;

                boolean overshoot = rawIndex > 24;

                int nextIndex = rawIndex;
                if (rawIndex > 24) {
                    int overflow = rawIndex - 24;
                    nextIndex = 24 - overflow;
                }

                int nextPos = blue.peekPosition(nextIndex);

                if (nextIndex != 24 && red.getPosition() == nextPos) {
                    System.out.printf("Blue moves from %d to %d 💥 (HIT → move forfeited)%n",
                            start, start);
                } else {
                    blue.advance(roll);
                    int pos = blue.getPosition();

                    if (wormholes.containsKey(pos)) {
                        int exit = wormholes.get(pos);
                        blue.setPosition(exit);
                        System.out.printf("Blue moves from %d to %d 🌀 (TELEPORT to %d)%n",
                                start, pos, exit);
                    } else if (overshoot) {
                        System.out.printf("Blue overshoots END and bounces back to Position %d%n", pos);
                    } else if (blue.isAtEnd()) {
                        System.out.printf("Blue moves from %d to End (Position %d)%n", start, pos);
                    } else {
                        System.out.printf("Blue moves from %d to %d%n", start, pos);
                    }
                }
            }
        }

        if (red.isAtEnd()) {
            System.out.printf("Red wins in %d turns.%n", redTurns);
        } else {
            System.out.printf("Blue wins in %d turns.%n", blueTurns);
        }

        System.out.printf("Total turns: %d%n", turns);
        System.out.println("Game State: InPlay → GameOver");
        System.out.println("Dice rolls: " + rolls);
        System.out.println("Game Id: 4 saved.");
    }
}
