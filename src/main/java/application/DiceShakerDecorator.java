//package application;
//
//public class DiceShakerDecorator implements DiceShaker {
//
//    private final DiceShaker component;
//    private final DiceShaker extra = new RandomSingleDiceShaker();
//
//    public DiceShakerDecorator(DiceShaker component) {
//        this.component = component;
//    }
//
//    @Override
//    public boolean hasNext() {
//        return component.hasNext();
//    }
//
//    @Override
//    public int next() {
//        // add another dice
//        return component.next() + extra.next();
//    }
//}