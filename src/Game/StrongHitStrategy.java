package Game;

public class StrongHitStrategy implements HitStrategy {

    @Override
    public boolean apply(Player current, Player... others) {
        for (Player p : others) {
            if (p.getPosition() == current.getPosition()) {
                p.setIndex(0); // send opponent to HOME
                System.out.println("💥 Strong HIT! " + current.getClass().getSimpleName() +
                        " hit " + p.getClass().getSimpleName() +
                        " → " + p.getClass().getSimpleName() + " goes HOME");
                return false; // current player stays
            }
        }
        return false;
    }
}