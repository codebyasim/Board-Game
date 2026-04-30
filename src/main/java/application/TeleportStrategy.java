package application;

import game.GameObserver;

public interface TeleportStrategy {
    void apply(Player player, String name, GameObserver observer);
}