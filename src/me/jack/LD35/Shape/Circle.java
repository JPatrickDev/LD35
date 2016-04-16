package me.jack.LD35.Shape;

import me.jack.LD35.Entity.Mob;
import me.jack.LD35.Entity.Player;

/**
 * Created by Jack on 16/04/2016.
 */
public class Circle extends Shape{
    public Circle() {
        super(new org.newdawn.slick.geom.Circle(0,0,8));
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
