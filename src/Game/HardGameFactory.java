package Game;

public class HardGameFactory implements GameFactory {

    @Override
    public DiceShaker createDiceShaker() {
        return new RandomDoubleDiceShaker(); // harder
    }

    @Override
    public PlayerSelector createPlayerSelector(Player[] players) {
    //    return new RandomSelector(players); // unpredictable
        return new RandomNoRepeatSelector(players);
   }


    @Override
    public HitStrategy createHitStrategy() {
        return new StrongHitStrategy(); // 🔥 stronger hit
    }

    @Override
    public TeleportStrategy createTeleportStrategy() {
        return new NoTeleportStrategy(); // 🚫 no teleport
    }
}