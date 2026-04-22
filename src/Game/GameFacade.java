
package Game;

public class GameFacade {

    public void play() {
        GameContext game = new GameContext(new StartState());

        game.play(); // StartState
        game.play(); // PlayingState (runs runGame)
        game.play(); // GameOverState
    }
}