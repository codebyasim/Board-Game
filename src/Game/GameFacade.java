package Game;

public class GameFacade {

    private final GameFactory factory; // 🔹 injected dependency

    // 🔹 constructor injection
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


//package Game;
//
//public class GameFacade {
//
//    public void play() {
//
//        int option = 2; // 1 = Easy, 2 = Hard
//
//        GameFactory factory = (option == 1)
//                ? new EasyGameFactory()
//                : new HardGameFactory();
//
//// ✅ correct label based on actual object
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