package me.jack.LD35;

import me.jack.LD35.States.GameOverState;
import me.jack.LD35.States.InGameState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Jack on 16/04/2016.
 */
public class LD35 extends StateBasedGame{

    public LD35() {
        super("XXXX for LD35 - Theme:Shapeshift");
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        addState(new InGameState());
        addState(new GameOverState());
    }
}
