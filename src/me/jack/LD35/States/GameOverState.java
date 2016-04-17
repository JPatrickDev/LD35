package me.jack.LD35.States;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Jack on 17/04/2016.
 */
public class GameOverState extends BasicGameState{
    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    boolean go = false;
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawString("gg",50,50);
        graphics.drawString("Press any key to play again",50,70);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(go) {
            stateBasedGame.enterState(1);
            go = false;
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        go = true;
    }
}
