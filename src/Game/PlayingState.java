package Game;

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

        GameFactory factory = context.getFactory();

        Player[] players = {red, blue, green, yellow};

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




