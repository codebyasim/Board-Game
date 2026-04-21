package Game;

public interface GameObserver {
    void onMove(String player, int from, int to);
    void onTeleport(String player, int from, int to);
    void onWin(String player, int rounds);
    void onRoll(String player, int round, int roll);
    void onHit(String attacker, String victim);
    void onBounce(String player, int position);
}