package application;

import java.util.Iterator;

public class DiceIterable implements Iterable<Integer> {

    private final DiceShaker shaker;

    public DiceIterable(DiceShaker shaker) {
        this.shaker = shaker;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new DiceIterator(shaker);
    }
}