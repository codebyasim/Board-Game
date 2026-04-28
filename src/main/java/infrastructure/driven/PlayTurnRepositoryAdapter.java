package infrastructure.driven;


import application.GameRepository;
import application.usecase.playturn.Required;
import org.springframework.stereotype.Component;

@Component
public class PlayTurnRepositoryAdapter implements Required {

    private final GameRepository repository;

    public PlayTurnRepositoryAdapter(GameRepository repository) {
        this.repository = repository;
    }

    @Override
    public String load() {
        return repository.loadGame();
    }
}