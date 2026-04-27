package application;

import java.util.Random;

public class RandomSelector implements PlayerSelector {

    private final Player[] players;
    private final Random random = new Random();

    public RandomSelector(Player[] players) {
        this.players = players;
    }

    @Override
    public Player next() {
        return players[random.nextInt(players.length)];
    }
}