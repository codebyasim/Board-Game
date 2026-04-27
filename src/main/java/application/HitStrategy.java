package application;

import Game.GameObserver;

public interface HitStrategy {
    boolean apply(Player current, GameObserver observer, Player... others);
}