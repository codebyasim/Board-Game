package application.usecase.endgame;

import org.springframework.stereotype.Component;

@Component("endGame")
public class UseCase implements Provided {

    private final Required required;

    public UseCase(Required required) {
        this.required = required;
    }

    @Override
    public Response handle(Request request) {
        System.out.println("Game Over!");

        required.save("Game finished");

        return new Response("Game Over");
    }
}