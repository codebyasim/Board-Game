package game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static game.basic.Main.*;

@SpringBootApplication(scanBasePackages = {"application", "infrastructure", "Game"})
public class GameApplication {

    public static void main(String[] args) {

        game.basic.Main.runGame1();

        System.out.println();

        runGame2();

        System.out.println();

        runGame3();

        System.out.println();

        runGame4();


        SpringApplication.run(GameApplication.class, args);
    }
}