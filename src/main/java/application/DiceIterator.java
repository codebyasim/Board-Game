package application;

import java.util.Iterator;

public class DiceIterator implements Iterator<Integer> {

    private final DiceShaker shaker;

    public DiceIterator(DiceShaker shaker) {
        this.shaker = shaker;
    }

    @Override
    public boolean hasNext() {
        return shaker.hasNext();
    }

    @Override
    public Integer next() {
        return shaker.next();
    }
}