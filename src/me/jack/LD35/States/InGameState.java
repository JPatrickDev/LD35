package me.jack.LD35.States;

import me.jack.LD35.Level.Level;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Jack on 16/04/2016.
 */
public class InGameState extends BasicGameState {


    private Level level;
    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        level = new Level(50,30);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        level.render(graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        level.update();
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        level.keyPressed(key,c);
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        super.mousePressed(button, x, y);
        level.mousePressed(button,x,y);
    }

    @Override
    public int getID() {
        return 1;
    }

}
