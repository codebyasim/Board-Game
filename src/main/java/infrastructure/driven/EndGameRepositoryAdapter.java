package infrastructure.driven;

import application.GameRepository;
import application.usecase.endgame.Required;
import org.springframework.stereotype.Component;

@Component
public class EndGameRepositoryAdapter implements Required {

    private final GameRepository repository;

    public EndGameRepositoryAdapter(GameRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(String result) {
        repository.saveGame(result);
    }
}