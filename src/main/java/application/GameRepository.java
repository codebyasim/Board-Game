package application;


public interface GameRepository {
    // example methods
    void saveGame(String state);
    String loadGame();
}