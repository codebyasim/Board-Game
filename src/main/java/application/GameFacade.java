
//package application;
//
//import Game.EasyGameFactory;
//import Game.GameFactory;
//import org.springframework.stereotype.Component;
//
//
//
//@Component
//public class GameFacade implements GameService {
//
//    private final GameFactory factory;
//
//    public GameFacade(GameFactory factory) {
//        this.factory = factory;
//    }
//
//    @Override
//    public void playGame() {
//        play();
//    }
//
//    public void play() {
//        String mode = (factory instanceof EasyGameFactory) ? "Easy" : "Hard";
//        System.out.println("Game Mode: " + mode);
//
//        GameContext game = new GameContext(new StartState(), factory);
//
//        game.play();
//        game.play();
//        game.play();
//    }
//}




//@Component
//public class GameFacade {
//
//    private final GameFactory factory;
//
//    //  constructor injection (Spring will provide it)
//    public GameFacade(GameFactory factory) {
//        this.factory = factory;
//    }
//
//
//
//    public void play() {
//        String mode = (factory instanceof EasyGameFactory) ? "Easy" : "Hard";
//        System.out.println("Game Mode: " + mode);
//
//        GameContext game = new GameContext(new StartState(), factory);
//
//        game.play();
//        game.play();
//        game.play();
//    }
//}


package application;

import Game.EasyGameFactory;
import Game.GameFactory;
import org.springframework.stereotype.Component;

@Component
public class GameFacade implements GameService {

    private final GameFactory factory;
    private final GameRepository repository;

    // ✅ Constructor injection (Spring will provide both)
    public GameFacade(GameFactory factory, GameRepository repository) {
        this.factory = factory;
        this.repository = repository;
    }

    @Override
    public void playGame() {
        play();
    }

    public void play() {
        String mode = (factory instanceof EasyGameFactory) ? "Easy" : "Hard";
        System.out.println("Game Mode: " + mode);

        // Example usage of repository (simple demo)
        repository.saveGame("Game started in " + mode + " mode");

        GameContext game = new GameContext(new StartState(), factory);

        game.play();
        game.play();
        game.play();

        // Example load (optional)
        System.out.println("Saved State: " + repository.loadGame());
    }
}