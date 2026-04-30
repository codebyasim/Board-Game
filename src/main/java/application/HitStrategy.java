package application;

import game.GameObserver;

public interface HitStrategy {
    boolean apply(Player current, GameObserver observer, Player... others);
}