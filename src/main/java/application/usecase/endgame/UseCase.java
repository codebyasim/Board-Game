package application.usecase.endgame;

import org.springframework.stereotype.Component;
import application.GameSession;

@Component("endGame")
public class UseCase implements Provided {

    private final Required required;

    private final GameSession session;

    public UseCase(Required required, GameSession session) {
        this.required = required;
        this.session = session;
    }

    @Override
    public Response handle(Request request) {

        System.out.println("Dice rolls: " + session.getDiceRolls());

        System.out.println("Game Over!");

        required.save("Game finished");

        return new Response("Game Over");
    }
}