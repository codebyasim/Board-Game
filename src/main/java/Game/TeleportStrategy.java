package Game;

public interface TeleportStrategy {
    void apply(Player player, String name, GameObserver observer);
}