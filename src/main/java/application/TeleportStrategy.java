package application;

import Game.GameObserver;

public interface TeleportStrategy {
    void apply(Player player, String name, GameObserver observer);
}