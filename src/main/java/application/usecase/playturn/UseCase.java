package application.usecase.playturn;

import application.GameSession;
import org.springframework.stereotype.Component;

@Component("playTurn")
public class UseCase implements Provided {

    private final GameSession session;
    private final Required required;

    public UseCase(GameSession session, Required required) {
        this.session = session;
        this.required = required;
    }

    @Override
    public Response handle(Request request) {
        int turn = request.getTurnNumber();

        System.out.println("Turn " + turn);

        String last = required.load();
        System.out.println("Loaded: " + last);

        session.game.play();

        return new Response("Turn " + turn + " completed");
    }
}