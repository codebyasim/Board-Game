package Game;

public class Main {

    public static void main(String[] args) {


        Player red = new Red();
        Player blue = new Blue();
        Player green = new Green();
        Player yellow = new Yellow();
        //L4S Factory
        GameFactory factory;
        // choose mode
        int option = 1;

        if (option == 1) {
            factory = new EasyGameFactory();
        } else {
            factory = new HardGameFactory();
        }
        // create everything from factory
        Player[] players = {red, blue, green, yellow};

        DiceShaker shaker = factory.createDiceShaker();
        PlayerSelector selector = factory.createPlayerSelector(players);
        HitStrategy hitStrategy = factory.createHitStrategy();
        TeleportStrategy teleportStrategy = factory.createTeleportStrategy();

        int round = 1;
        //count total plays
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

                // pass other players
                Player[] others = java.util.Arrays.stream(players)
                        .filter(p -> p != current)
                        .toArray(Player[]::new);

                if (playTurn(name, current, shaker, others[0], others[1], others[2], round, hitStrategy,
                         teleportStrategy)) {
                    break;
                }

                // increase round after full cycle
                if (current == players[players.length - 1]) {
                    round++;
                    System.out.println();
                }
            }
        //print total plays at end
        System.out.println("Total plays " + totalPlays);
    }

    public static boolean playTurn(String name, Player player, DiceShaker shaker,
                                   Player p1, Player p2, Player p3, int round, HitStrategy hitStrategy,
                                   TeleportStrategy teleportStrategy) {

        //Iterator from L2S
        for (int roll : new DiceIterable(shaker)) {

            int oldPos = player.getPosition();
            int oldIndex = player.getIndex();

            System.out.println(name + " play " + round + " rolls " + roll);

            player.advance(roll, name);

            // L3S Teleport + Hit Strategy calls
            teleportStrategy.apply(player, name);

            if (hitStrategy.apply(player, p1, p2, p3)) {
                System.out.println("💥 " + name + " HIT! stays at " + oldPos);
                player.setIndex(oldIndex);
            }

            System.out.println(name + " moves from " + oldPos + " to " + player);

            if (player.isAtEnd()) {
                System.out.println(name + " wins in " + round + " rounds");
                return true;
            }

            break; // IMPORTANT
        }
        return false;
    }
}