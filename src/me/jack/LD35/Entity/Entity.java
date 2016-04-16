package me.jack.LD35.Entity;

import me.jack.LD35.Level.Level;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Jack on 16/04/2016.
 */
public abstract class Entity {
    private float x, y;
    private int w, h;

    public static SpriteSheet sprites;

    public Entity(float x, float y, int w, int h) {
        if (sprites == null)
            try {
                sprites = new SpriteSheet(new Image("res/sprites.png"), 16, 16);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public abstract void render(Graphics g);

    public abstract void update(Level level);

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public void addX(float x) {
        this.x += x;
    }

    public void addY(float y) {
        this.y += y;
    }
}
