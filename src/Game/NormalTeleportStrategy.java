package Game;

public class NormalTeleportStrategy implements TeleportStrategy {

    @Override
    public void apply(Player player, String name, GameObserver observer) {

        int pos = player.getPosition();
        int newPos = pos;

        if (pos == 3) newPos = 34;
        else if (pos == 34) newPos = 3;
        else if (pos == 8) newPos = 22;
        else if (pos == 22) newPos = 8;

        if (newPos != pos) {
            //System.out.println("🌀 " + name + " teleports from " + pos + " to " + newPos);
            observer.onTeleport(name, pos, newPos);

            for (int i = 0; i < 36; i++) {
                if (player.POSITIONS[i] == newPos) {
                    player.setIndex(i);
                    break;
                }
            }
        }
    }
}