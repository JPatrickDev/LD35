package me.jack.LD35;

import me.jack.LD35.States.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Jack on 16/04/2016.
 */
public class LD35 extends StateBasedGame{

    public LD35() {
        super("Squared for LD35 - Theme:Shapeshift");
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        gameContainer.setShowFPS(false);
        addState(new MainMenuState());
        addState(new InGameState());
        addState(new GameOverState());
        addState(new AboutState());
        addState(new InstructionsState());
    }
}
