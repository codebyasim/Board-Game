package infrastructure.driving;

import application.GameService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final GameService gameService;

    public Runner(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) {
        gameService.playGame();
    }
}
