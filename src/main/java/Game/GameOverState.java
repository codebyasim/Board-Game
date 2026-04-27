package Game;

public class GameOverState implements GameState {

    @Override
    public void play(GameContext context) {
        System.out.println("Game Over!");
    }
}