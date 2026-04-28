
package application.usecase.playturn;

public class Request {
    private final int turnNumber;

    public Request(int turnNumber) {
        if (turnNumber <= 0) {
            throw new IllegalArgumentException("turnNumber must be > 0");
        }
        this.turnNumber = turnNumber;
    }

    public int getTurnNumber() {
        return turnNumber;
    }
}