package Game;

public class GameContext {

    private GameState state;

    public GameContext(GameState state) {
        this.state = state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void play() {
        state.play(this);
    }
}