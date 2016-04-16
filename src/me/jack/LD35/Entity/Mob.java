package me.jack.LD35.Entity;

import me.jack.LD35.Level.Level;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by Jack on 16/04/2016.
 */
public abstract class Mob extends Entity {


    public static float g = 0.5f;
    protected float yVel = 0;

    public Mob(int x, int y) {
        super(x, y);
    }

    @Override
    public void update(Level level) {

        Rectangle rect = level.canMove(getX(), getY() + yVel, 16, 16);
        if (rect == null) {
            addY(yVel);
        } else {
            Rectangle test = level.canMove(getX(), getY() + 1, 16, 16);
            if(test == null) {
                float diff = getY() - rect.getY();
                diff +=16;
                System.out.println(diff);
                addY(diff);
                yVel = 0;
                return;
            }
            else {
                yVel = 0;
                return;
            }
        }
        yVel += g;
    }


}


