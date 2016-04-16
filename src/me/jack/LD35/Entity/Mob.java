package me.jack.LD35.Entity;

import me.jack.LD35.Level.Level;
import me.jack.LD35.Shape.Circle;
import me.jack.LD35.Shape.Square;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by Jack on 16/04/2016.
 */
public abstract class Mob extends Entity {


    public Mob(int x, int y) {
        super(x, y);
    }

    public float health = 10f;

    @Override
    public void update(Level level) {
        if(health <= 0){
            level.entities.remove(this);
            return;
        }
    }


}


