package Game;

public class PlayingState implements GameState {

    @Override
    public void play(GameContext context) {
        System.out.println("Game Playing...");

        // 👉 call your existing game logic here
        Main.runGame();   // we move your loop into a method

        context.setState(new GameOverState());
    }
}