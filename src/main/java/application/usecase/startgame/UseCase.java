package application.usecase.startgame;

import game.EasyGameFactory;
import application.GameContext;
import game.GameFactory;
import application.GameSession;
import application.StartState;
import org.springframework.stereotype.Component;

@Component("startGame")
public class UseCase implements Provided {

    private final GameFactory factory;
    private final GameSession session;
    private final Required required;

    public UseCase(GameFactory factory, GameSession session, Required required) {
        this.factory = factory;
        this.session = session;
        this.required = required;
    }

    @Override
    public Response handle(Request request) {

        String mode = (factory instanceof EasyGameFactory) ? "Easy" : "Hard";

        session.game = new GameContext(new StartState(session), factory);

        required.save("Game started");

        session.game.play();

        return new Response("Game started successfully");
    }
}