package infrastructure.driven;


import application.GameRepository;
import org.springframework.stereotype.Component;

@Component
public class InMemoryGameRepository implements GameRepository {

    private String state;

    @Override
    public void saveGame(String state) {
        this.state = state;
    }

    @Override
    public String loadGame() {
        return state;
    }
}

