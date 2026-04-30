package application;

import game.*;

import java.util.Stack;

public class PlayingState implements GameState {

    // ✅ state internal data
    private int round = 1;
    private int totalPlays = 0;

    @Override
    public void play(GameContext context) {

        System.out.println("Game Playing...");

        Player red = new Red();
        Player blue = new Blue();
        Player green = new Green();
        Player yellow = new Yellow();


        Player[] players = {red, blue, green, yellow};

        System.out.println("Board: rows=6 columns=6");
        System.out.println("Number of players: " + players.length);

        System.out.println("Red Home (Position 1), 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, End (Position 36)");
        System.out.println("Blue Home (Position 31), 32, 33, 34, 35, 36, 25, 26, 27, 28, 29, 30, 19, 20, 21, 22, 23, 24, 13, 14, 15, 16, 17, 18, 7, 8, 9, 10, 11, 12, 1, 2, 3, 4, 5, End (Position 6)");
        System.out.println("Yellow Home (Position 36), 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, End (Position 1)");
        System.out.println("Green Home (Position 6), 5, 4, 3, 2, 1, 12, 11, 10, 9, 8, 7, 18, 17, 16, 15, 14, 13, 24, 23, 22, 21, 20, 19, 30, 29, 28, 27, 26, 25, 36, 35, 34, 33, 32, End (Position 31)");

        System.out.println();


        GameFactory factory = context.getFactory();


        DiceShaker shaker = factory.createDiceShaker();
        PlayerSelector selector = factory.createPlayerSelector(players);
        HitStrategy hitStrategy = factory.createHitStrategy();
        TeleportStrategy teleportStrategy = factory.createTeleportStrategy();
        GameObserver observer = new ConsoleObserver();
        GameMediator mediator = new GameMediator();

        Stack<Command> history = new Stack<>();

        while (true) {

            Player current = selector.next();
            totalPlays++; // ✅ now stored in state

            String name = current.getClass().getSimpleName();

            Player[] others = java.util.Arrays.stream(players)
                    .filter(p -> p != current)
                    .toArray(Player[]::new);

            for (int roll : new DiceIterable(shaker)) {

                boolean finished = mediator.handleTurn(
                        name, current, roll,
                        others[0], others[1], others[2],
                        round, // ✅ uses state field
                        hitStrategy,
                        teleportStrategy,
                        observer,
                        history
                );

                if (finished) {
                    System.out.println("Total plays " + totalPlays);

                    context.setState(new GameOverState());
                    return;
                }

                break;
            }

            if (current == players[players.length - 1]) {
                round++; // ✅ persists inside state
                System.out.println();
            }
        }
    }
}


