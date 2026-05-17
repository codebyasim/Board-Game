package application;


public class StartState implements GameState {

    private final GameSession session;

    public StartState(GameSession session) {
        this.session = session;
    }

    @Override
    public void play(GameContext context) {
        System.out.println("Game Starting...");
        context.setState(new PlayingState(session));
    }
}

