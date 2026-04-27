package Game;

public class ForwardSelector implements PlayerSelector {

    private final Player[] players;
    private int index = 0;

    public ForwardSelector(Player[] players) {
        this.players = players;
    }

    @Override
    public Player next() {
        Player current = players[index];
        index = (index + 1) % players.length; // loop
        return current;
    }
}