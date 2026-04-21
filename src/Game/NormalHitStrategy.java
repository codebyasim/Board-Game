package Game;

public class NormalHitStrategy implements HitStrategy {

    @Override
    public boolean apply(Player current, GameObserver observer, Player... others) {
        for (Player p : others) {
            if (p != current && p.getPosition() == current.getPosition()) {

                String attacker = current.getClass().getSimpleName();
                String victim = p.getClass().getSimpleName();

                observer.onHit(attacker, victim);

                return true;
            }
        }
        return false;
    }
}