package Game;

import java.util.Stack;

public class PlayingState implements GameState {

    @Override
    public void play(GameContext context) {

        System.out.println("Game Playing...");

        Player red = new Red();
        Player blue = new Blue();
        Player green = new Green();
        Player yellow = new Yellow();

        GameFactory factory = context.getFactory(); // can improve later (DIP)

        Player[] players = {red, blue, green, yellow};

        DiceShaker shaker = factory.createDiceShaker();
        PlayerSelector selector = factory.createPlayerSelector(players);
        HitStrategy hitStrategy = factory.createHitStrategy();
        TeleportStrategy teleportStrategy = factory.createTeleportStrategy();
        GameObserver observer = new ConsoleObserver();
        GameMediator mediator = new GameMediator();

        Stack<Command> history = new Stack<>();

        int round = 1;
        int totalPlays = 0;

        while (true) {

            Player current = selector.next();
            totalPlays++;

            String name = current.getClass().getSimpleName();

            Player[] others = java.util.Arrays.stream(players)
                    .filter(p -> p != current)
                    .toArray(Player[]::new);

            for (int roll : new DiceIterable(shaker)) {

                boolean finished = mediator.handleTurn(
                        name, current, roll,
                        others[0], others[1], others[2],
                        round,
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

                break; // one roll per turn
            }

            if (current == players[players.length - 1]) {
                round++;
                System.out.println();
            }
        }
    }
}