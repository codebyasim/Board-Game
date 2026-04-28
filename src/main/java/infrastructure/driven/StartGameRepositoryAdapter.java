package infrastructure.driven;

import application.GameRepository;
import application.usecase.startgame.Required;
import org.springframework.stereotype.Component;

@Component
public class StartGameRepositoryAdapter implements Required {

    private final GameRepository repository;

    public StartGameRepositoryAdapter(GameRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(String state) {
        repository.saveGame(state);
    }
}