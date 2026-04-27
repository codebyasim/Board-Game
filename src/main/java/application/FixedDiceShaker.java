package application;

public class FixedDiceShaker implements DiceShaker {

    private final int[] rolls = {2,4,6,3,1,5};
    private int index = 0;

    @Override
    public boolean hasNext() {
        return index < rolls.length;
    }

    @Override
    public int next() {
        return rolls[index++];
    }
}