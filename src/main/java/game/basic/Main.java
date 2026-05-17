package game.basic;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        runGame1();
        // runGame2();
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
}

