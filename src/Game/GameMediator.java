package Game;

public class GameMediator {

    public boolean handleTurn(String name, Player player, int roll,
                              Player p1, Player p2, Player p3,
                              int round,
                              HitStrategy hitStrategy,
                              TeleportStrategy teleportStrategy,
                              GameObserver observer,
                              java.util.Stack<Command> history) {

        int oldPos = player.getPosition();
        int oldIndex = player.getIndex();

        observer.onRoll(name, round, roll);

        Command move = new AdvanceCommand(player, roll, name);
        move.execute();
        history.push(move);

        // Bounce
        if (oldIndex + roll > 35) {
            observer.onBounce(name, player.getPosition());
        }

        // Teleport
        teleportStrategy.apply(player, name, observer);

        // Hit
        if (hitStrategy.apply(player, observer, p1, p2, p3)) {
            player.setIndex(oldIndex);
        }

        observer.onMove(name, oldPos, player.getPosition());

        if (player.isAtEnd()) {
            observer.onWin(name, round);
            return true;
        }

        return false;
    }
}