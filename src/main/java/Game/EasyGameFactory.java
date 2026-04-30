package game;

import application.*;

public class EasyGameFactory implements GameFactory {

    @Override
    public DiceShaker createDiceShaker() {
        return new RandomSingleDiceShaker();
    }

    @Override
    public PlayerSelector createPlayerSelector(Player[] players) {
        return new ForwardSelector(players);
    }

    @Override
    public HitStrategy createHitStrategy() {
        return new NormalHitStrategy();
    }

    @Override
    public TeleportStrategy createTeleportStrategy() {
        return new NormalTeleportStrategy();
    }
}