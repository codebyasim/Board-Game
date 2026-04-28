package application;

import Game.GameFactory;
import application.GameState;

public class GameContext {

    private GameState state;
    private final GameFactory factory;

    public GameContext(GameState state, GameFactory factory) {
        this.state = state;
        this.factory = factory;
    }

    public GameFactory getFactory() {
        return factory;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void play() {
        state.play(this);
    }
}