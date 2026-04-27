
package Game;

import org.springframework.stereotype.Component;

@Component
public class GameFacade {

    private final GameFactory factory;

    //  constructor injection (Spring will provide it)
    public GameFacade(GameFactory factory) {
        this.factory = factory;
    }

    public void play() {
        String mode = (factory instanceof EasyGameFactory) ? "Easy" : "Hard";
        System.out.println("Game Mode: " + mode);

        GameContext game = new GameContext(new StartState(), factory);

        game.play();
        game.play();
        game.play();
    }
}