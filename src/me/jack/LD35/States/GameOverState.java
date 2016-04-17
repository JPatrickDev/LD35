package me.jack.LD35.States;

import me.jack.LD35.Level.Level;
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
    static Level level;
    int wait = 0;
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawString("GAME OVER!",50,50);
        graphics.drawString("Round: " + level.round,50,65);
        graphics.drawString("Level: " + level.getPlayer().level,50,80);
        int score = (int) (2*(level.round*level.round) + 5*level.getPlayer().level + 5);
        graphics.drawString("Score: " + score,50,95);
        graphics.drawString("Press any key to play again",50,110);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(go && wait > 50) {
            stateBasedGame.enterState(1);
            go = false;
        }
        wait++;
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        go = true;
    }
}
