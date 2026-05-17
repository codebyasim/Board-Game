package application;

import game.*;

import java.util.Stack;

public class PlayingState implements GameState {

    // ✅ state internal data
    private int round = 1;
    private int totalPlays = 0;

    private final GameSession session;

    public PlayingState(GameSession session) {
        this.session = session;
    }

    @Override
    public void play(GameContext context) {


        System.out.println("Board: Large, Players: 4, Exact End + Bounce Back, Random Double Dice, Hit Rule Applied, Wormholes exist: 3: 34, 8: 22, Teleport Enabled");
        System.out.println("Game Playing...");

        Player red = new Red();
        Player blue = new Blue();
        Player yellow = new Yellow();
        Player green = new Green();


        Player[] players = {red, blue, yellow, green};

        System.out.println("Board: rows=6 columns=6");
        System.out.println("Number of players: " + players.length);

        System.out.println("Red Home (Position 1)");
        System.out.println("Blue Home (Position 31)");
        System.out.println("Yellow Home (Position 36)");
        System.out.println("Green Home (Position 6)");

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

                session.getDiceRolls().add(roll);

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


