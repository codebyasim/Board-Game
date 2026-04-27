package Game;

import java.util.NoSuchElementException;

public class NullDiceShaker implements DiceShaker {

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public int next() {
        throw new NoSuchElementException();
    }
}