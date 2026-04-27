package application;


import java.util.Random;

public class RandomNoRepeatSelector implements PlayerSelector {

    private final Player[] players;
    private final Random random = new Random();
    private Player lastPlayer = null;

    public RandomNoRepeatSelector(Player[] players) {
        this.players = players;
    }

    @Override
    public Player next() {
        Player next;
        do {
            next = players[random.nextInt(players.length)];
        } while (next == lastPlayer);

        lastPlayer = next;
        return next;
    }
}