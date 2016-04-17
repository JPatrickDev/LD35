package me.jack.LD35.States;

import me.jack.LD35.Entity.EntityRobot;
import me.jack.LD35.GUI.DisplayShapeInfo;
import me.jack.LD35.GUI.HealthInfo;
import me.jack.LD35.GUI.LevelInfo;
import me.jack.LD35.GUI.ShapeSelect;
import me.jack.LD35.Level.Level;
import me.jack.LD35.Level.Tile.Tile;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Jack on 16/04/2016.
 */
public class InGameState extends BasicGameState {

    Level level;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Tile.init();
        level = new Level(50, 30);

    }

    public boolean paused = false;
    public static int showing = -1;

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        level.render(graphics);
        ShapeSelect.render(graphics, level);
        LevelInfo.render(graphics, level);
        HealthInfo.render(graphics,level);
        if(DisplayShapeInfo.displaying){
            graphics.setColor(shade);
            graphics.fillRect(0,0,800,(30*16));
            graphics.setColor(Color.white);
        }
        if(showing != -1)
        DisplayShapeInfo.displayInfo(showing,graphics);
    }

    Color shade = new Color(0,0,0,240);

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(!paused && !DisplayShapeInfo.displaying) {
            level.update();
            if (Mouse.isButtonDown(0)) {
                Input in = gameContainer.getInput();
                level.pressed(in.getMouseX(), in.getMouseY(), 0);
            }
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        level.keyPressed(key, c);

    }

    @Override
    public void mousePressed(int button, int x, int y) {
        super.mousePressed(button, x, y);
        level.clicked(x, y, button);
        if(button == 0) {

            ShapeSelect.mouseClicked(x, y);
            if(showing != -1){
                DisplayShapeInfo.mouseClick(x,y);
            }
        }
    }



    @Override
    public int getID() {
        return 1;
    }

    public static void showInfo(int i) {
        InGameState.showing = i;
    }
}
