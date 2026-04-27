package application;

import Game.GameObserver;

public class NormalHitStrategy implements HitStrategy {

    @Override
    public boolean apply(Player current, GameObserver observer, Player... others) {

        for (Player p : others) {
            if (p != current && p.getPosition() == current.getPosition()) {

                // notify only (no movement)
                observer.onHit(
                        current.getClass().getSimpleName(),
                        p.getClass().getSimpleName()
                );

                return true; // 🔥 important → attacker stays
            }
        }
        return false;
    }
}