package application;


import java.util.Random;

public class RandomDoubleDiceShaker implements DiceShaker {

    private final Random random = new Random();

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public int next() {
        int dice1 = random.nextInt(6) + 1;
        int dice2 = random.nextInt(6) + 1;

        return dice1 + dice2;
    }
}