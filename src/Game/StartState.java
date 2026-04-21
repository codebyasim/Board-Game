package Game;


public class StartState implements GameState {

    @Override
    public void play(GameContext context) {
        System.out.println("Game Starting...");
        context.setState(new PlayingState());
    }
}