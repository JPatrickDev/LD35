package me.jack.LD35.Entity;

import me.jack.LD35.Level.Level;
import me.jack.LD35.Shape.Circle;
import me.jack.LD35.Shape.Square;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by Jack on 16/04/2016.
 */
public abstract class Mob extends Entity {


    public static float g = 1;
    protected float yVel = 0;
    public Mob(int x, int y) {
        super(x, y);
    }

    public float health = 10f;

    float tV = 16;

    boolean jumping = false,falling = false;
    @Override
    public void update(Level level) {
        if(health <= 0){
            level.mobs.remove(this);
            return;
        }
        Rectangle rect = level.canMove(getX(), getY() + yVel, 16, 16);
        if (rect == null) {
            addY(yVel);
        } else {
            Rectangle test = level.canMove(getX(), getY() + 1, 16, 16);
            if(test == null) {
                if(rect.getY() > getY() + yVel) {
                    float diff = rect.getY() - getY();
                    diff -= 16;
                    addY(diff);
                    yVel = 0;
                    jumping = false;
                    falling = false;
                    return;
                }else{
                    float diff =  getY() - rect.getY();
                    diff -= 16;
                    addY(-diff+0.05f);
                    yVel = 0;
                    jumping = false;
                    falling = true;
                    return;
                }
            }
            else {
                yVel = 0;
                return;
            }
        }
        yVel += g;
        if(yVel >= tV){
            yVel = tV;
        }
        falling = true;


    }

    public void jump() {
        if(jumping || falling)
            return;
        yVel = -16;
        jumping = true;
        falling = false;
    }
}


