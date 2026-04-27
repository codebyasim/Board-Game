package Game;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final GameFacade facade;

    public Runner(GameFacade facade) {
        this.facade = facade;
    }

    @Override
    public void run(String... args) {
        facade.play();
    }
}