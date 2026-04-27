package Game;

public interface HitStrategy {
    boolean apply(Player current, GameObserver observer, Player... others);
}