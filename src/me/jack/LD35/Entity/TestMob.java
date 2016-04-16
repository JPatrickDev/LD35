package me.jack.LD35.Entity;

import me.jack.LD35.Level.Level;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 16/04/2016.
 */
public class TestMob extends Mob{
    public TestMob(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.pink);
    g.fillRect(getX(),getY(),16,16);
        g.setColor(Color.white);
    }

    @Override
    public void update(Level level){
        super.update(level);
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            yVel = -16;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D))
            addX(5);
        if(Keyboard.isKeyDown(Keyboard.KEY_A))
            addX(-5);
    }




}
