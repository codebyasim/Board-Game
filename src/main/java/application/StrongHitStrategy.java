//package application;
//
//import game.GameObserver;
//
//public class StrongHitStrategy implements HitStrategy {
//
//    @Override
//    public boolean apply(Player current, GameObserver observer, Player... others) {
//        for (Player p : others) {
//            if (p != current && p.getPosition() == current.getPosition()) {
//
//                // send opponent to HOME
//                p.setIndex(0);
//
//                // clean readable variables
//                String attacker = current.getClass().getSimpleName();
//                String victim = p.getClass().getSimpleName();
//
//                // notify observer
//                observer.onStrongHit(attacker, victim);
//
//                return false; // current player stays
//            }
//        }
//        return false;
//    }
//}