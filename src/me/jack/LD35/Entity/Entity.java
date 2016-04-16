package me.jack.LD35.Entity;

import me.jack.LD35.Level.Level;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 16/04/2016.
 */
public abstract class Entity{
    private float x,y;

    public Entity(int x,int y){
        this.x = x;
        this.y = y;
    }

    public abstract void render(Graphics g);
    public abstract void update(Level level);

    public void addY(float add){
        this.y +=add;
    }
    public void addX(int x) {
        this.x += x;
    }
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
