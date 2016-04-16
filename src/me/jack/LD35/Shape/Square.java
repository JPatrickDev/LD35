package me.jack.LD35.Shape;

import me.jack.LD35.Entity.Mob;
import me.jack.LD35.Entity.Player;

/**
 * Created by Jack on 16/04/2016.
 */
public class Square extends Shape{
    public Square() {
        super(new org.newdawn.slick.geom.Rectangle(0,0,16,16));
    }

    @Override
    public void jump(Mob mob) {

    }

    @Override
    public void attack(Mob mob) {

    }

    @Override
    public void takeDamage(Mob mob, float damage) {

    }
}
