package application;


public class ReverseSelector implements PlayerSelector {

    private final Player[] players;
    private int index;

    public ReverseSelector(Player[] players) {
        this.players = players;
        this.index = players.length - 1;
    }

    @Override
    public Player next() {
        Player current = players[index];
        index = (index - 1 + players.length) % players.length;
        return current;
    }
}