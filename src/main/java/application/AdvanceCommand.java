package application;

public class AdvanceCommand implements Command {

    private final Player player;
    private final int roll;
    private final String name;

    private int previousIndex;

    public AdvanceCommand(Player player, int roll, String name) {
        this.player = player;
        this.roll = roll;
        this.name = name;
    }

    @Override
    public void execute() {
        previousIndex = player.getIndex();
        player.advance(roll, name);
    }

    @Override
    public void undo() {
        player.setIndex(previousIndex);
    }
}