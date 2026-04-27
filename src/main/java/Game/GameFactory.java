package Game;

import application.*;

public interface GameFactory {
    DiceShaker createDiceShaker();
    PlayerSelector createPlayerSelector(Player[] players);
    HitStrategy createHitStrategy();
    TeleportStrategy createTeleportStrategy();
}