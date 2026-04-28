
package application;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameFacade implements GameService {

    private final application.usecase.startgame.Provided startGame;
    private final application.usecase.playturn.Provided playTurn;
    private final application.usecase.endgame.Provided endGame;
    private final Environment env;

    public GameFacade(
            Environment env,
            @Qualifier("startGame") application.usecase.startgame.Provided startGame,
            @Qualifier("playTurn") application.usecase.playturn.Provided playTurn,
            @Qualifier("endGame") application.usecase.endgame.Provided endGame
    ) {
        this.env = env;
        this.startGame = startGame;
        this.playTurn = playTurn;
        this.endGame = endGame;
    }

    @Override
    public void playGame() {

        String mode = env.getActiveProfiles()[0]; // gets easy/hard

        startGame.handle(new application.usecase.startgame.Request(mode));

        playTurn.handle(new application.usecase.playturn.Request(1));

        endGame.handle(new application.usecase.endgame.Request());
    }
}