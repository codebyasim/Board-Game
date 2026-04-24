package Game;

public class Main {

    public static void main(String[] args) {

        // 🔹 choose implementation here Easy and Hard game
        //GameFactory factory = new HardGameFactory();
         GameFactory factory = new EasyGameFactory();

        // 🔹 inject into facade
        GameFacade facade = new GameFacade(factory);

        facade.play();   // only entry point
    }
}