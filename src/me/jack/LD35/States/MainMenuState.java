package me.jack.LD35.States;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Jack on 17/04/2016.
 */
public class MainMenuState extends BasicGameState{
    @Override
    public int getID() {
        return 0;
    }

    Image bg = null;

    Rectangle start = new Rectangle(203,194,389,67);
    Rectangle instructions = new Rectangle(191,267,427,70);
    Rectangle about = new Rectangle(188,340,411,71);
    static StateBasedGame instance = null;
    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        bg = new Image("res/mainMenu.png");
        instance = stateBasedGame;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(bg,0,0);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    public void mousePressed(int button, int x, int y) {
        super.mousePressed(button, x, y);
        if(button == 0){
            if(start.contains(x,y)){
                instance.enterState(1);
            }else if(instructions.contains(x,y)){
                instance.enterState(4);
            }else if(about.contains(x,y)){
                instance.enterState(3);
            }
        }
    }
}
