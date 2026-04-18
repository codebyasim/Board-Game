package Game;

public class RandomDoubleDiceShaker implements DiceShaker {

    private final DiceShaker shaker = new RandomSingleDiceShaker();

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public int next() {
        return shaker.next() + shaker.next();
    }
}