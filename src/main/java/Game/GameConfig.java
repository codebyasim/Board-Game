package Game;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class GameConfig {

    @Bean
    @Profile("easy")
    public GameFactory easyFactory() {
        return new EasyGameFactory();
    }

    @Bean
    @Profile("hard")
    public GameFactory hardFactory() {
        return new HardGameFactory();
    }
}