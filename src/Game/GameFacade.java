package Game;

public class GameFacade {

    public void play() {

        int option = 1; // 1 = Easy, 2 = Hard

        GameFactory factory = (option == 1)
                ? new EasyGameFactory()
                : new HardGameFactory();

        String mode = (option == 1) ? "Easy" : "Hard";
        System.out.println("Game Mode: " + mode);

        GameContext game = new GameContext(new StartState(), factory);

        game.play();
        game.play();
        game.play();
    }
}