package Game;

public class NormalHitStrategy implements HitStrategy {

    @Override
    public boolean apply(Player current, Player... others) {
        for (Player p : others) {
            if (p.getPosition() == current.getPosition()) {
                return true;
            }
        }
        return false;
    }
}
