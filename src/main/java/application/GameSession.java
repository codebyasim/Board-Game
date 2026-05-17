//package application;
//
//import org.springframework.stereotype.Component;
//
//@Component
//public class GameSession {
//    public GameContext game;
//}

package application;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameSession {

    public GameContext game;

    private final List<Integer> diceRolls = new ArrayList<>();

    public List<Integer> getDiceRolls() {
        return diceRolls;
    }
}