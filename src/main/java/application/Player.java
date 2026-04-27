package application;

public abstract class Player implements Comparable<Player> {

    protected int currentIndex = 0;
    protected final int[] POSITIONS;

    public Player(int[] positions) {
        this.POSITIONS = positions;
    }

    public int getPosition() {
        return POSITIONS[currentIndex];
    }

    public int getIndex() {
        return currentIndex;
    }

    public void setIndex(int index) {
        this.currentIndex = index;
    }

    public boolean isHome() {
        return currentIndex == 0;
    }

    public boolean isAtEnd() {
        return currentIndex == 35;
    }

    public void advance(int steps, String name) {
        currentIndex += steps;

        if (currentIndex > 35) {
            int extra = currentIndex - 35;
            currentIndex = 35 - extra;
        }
    }
    // Shows all player home positions
    @Override
    public String toString() {
        if (isHome()) return "HOME (Position " + getPosition() + ")";
        if (isAtEnd()) return "END (Position " + getPosition() + ")";
        return "Position " + getPosition();
    }

    @Override
    public int compareTo(Player other) {
        if (other == null) throw new NullPointerException();
        return Integer.compare(this.getPosition(), other.getPosition());
    }
}