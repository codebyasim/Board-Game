
package Game;

public class Main {

    // 🔹 Main only controls states
    public static void main(String[] args) {

        GameContext game = new GameContext(new StartState());

        game.play(); // Start
        game.play(); // Playing
        game.play(); // GameOver
    }

    // 🔹 Your full game logic moved here
    public static void runGame() {

        Player red = new Red();
        Player blue = new Blue();
        Player green = new Green();
        Player yellow = new Yellow();

        GameFactory factory;
        int option = 2; // 1 = Easy, 2 = Hard

        if (option == 1) {
            factory = new EasyGameFactory();
        } else {
            factory = new HardGameFactory();
        }

        Player[] players = {red, blue, green, yellow};

        DiceShaker shaker = factory.createDiceShaker();
        PlayerSelector selector = factory.createPlayerSelector(players);
        HitStrategy hitStrategy = factory.createHitStrategy();
        TeleportStrategy teleportStrategy = factory.createTeleportStrategy();
        GameObserver observer = new ConsoleObserver();

        int round = 1;
        int totalPlays = 0;

        String mode = (option == 1) ? "Easy" : "Hard";
        System.out.println("Game Mode: " + mode);

        System.out.println("Red: " + red);
        System.out.println("Blue: " + blue);
        System.out.println("Green: " + green);
        System.out.println("Yellow: " + yellow);
        System.out.println();

        while (true) {

            Player current = selector.next();
            totalPlays++;

            String name = current.getClass().getSimpleName();

            Player[] others = java.util.Arrays.stream(players)
                    .filter(p -> p != current)
                    .toArray(Player[]::new);

            if (playTurn(name, current, shaker,
                    others[0], others[1], others[2],
                    round, hitStrategy, teleportStrategy, observer)) {
                break;
            }

            if (current == players[players.length - 1]) {
                round++;
                System.out.println();
            }
        }

        System.out.println("Total plays " + totalPlays);
    }

    // 🔹 Keep playTurn separate (good design)
    public static boolean playTurn(String name, Player player, DiceShaker shaker,
                                   Player p1, Player p2, Player p3,
                                   int round, HitStrategy hitStrategy,
                                   TeleportStrategy teleportStrategy,
                                   GameObserver observer ) {

        for (int roll : new DiceIterable(shaker)) {

            int oldPos = player.getPosition();
            int oldIndex = player.getIndex();

            observer.onRoll(name, round, roll);

            player.advance(roll, name);


            teleportStrategy.apply(player, name, observer);

            if (oldIndex + roll > 35) {
                observer.onBounce(name, player.getPosition());
            }

            if (hitStrategy.apply(player, observer, p1, p2, p3)) {
                player.setIndex(oldIndex);
            }


            observer.onMove(name, oldPos, player.getPosition());

            if (player.isAtEnd()) {
                observer.onWin(name, round);
                return true;
            }

            break;
        }
        return false;
    }
}