package Game;

public class NoTeleportStrategy implements TeleportStrategy {

    @Override
    public void apply(Player player, String name) {
        // do nothing (teleport disabled)
    }
}