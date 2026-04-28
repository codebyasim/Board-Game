package application.usecase.startgame;

public class Request {
    private final String mode;

    public Request(String mode) {
        if (mode == null || mode.isBlank()) {
            throw new IllegalArgumentException("mode required");
        }
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}