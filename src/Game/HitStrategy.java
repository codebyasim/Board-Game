package Game;

public interface HitStrategy {
    boolean apply(Player current, Player... others);
}