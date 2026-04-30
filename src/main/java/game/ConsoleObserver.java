package game;

public class ConsoleObserver implements GameObserver {

    @Override
    public void onMove(String player, int from, int to) {
        System.out.println(player + " moves from " + from + " to " + to);
    }

    @Override
    public void onTeleport(String player, int from, int to) {
        System.out.println("🌀 " + player + " teleports from " + from + " to " + to);
    }

    @Override
    public void onWin(String player, int rounds) {
        System.out.println(player + " wins in " + rounds + " rounds");
    }

    @Override
    public void onBounce(String player, int position) {
        System.out.println("↩️ " + player + " bounced to " + position);
    }

    @Override
    public void onRoll(String player, int round, int roll) {
        System.out.println(player + " play " + round + " rolls " + roll);
    }

    @Override
    public void onHit(String attacker, String victim) {
        System.out.println("💥 " + attacker + " hit " + victim);
    }

    @Override
    public void onStrongHit(String attacker, String victim) {
        System.out.println("💥 STRONG HIT:" + attacker + " hit " + victim + " → " + victim + " goes HOME");
    }
}