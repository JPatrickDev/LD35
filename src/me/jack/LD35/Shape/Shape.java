package me.jack.LD35.Shape;

import me.jack.LD35.Entity.Mob;

/**
 * Created by Jack on 16/04/2016.
 */
public abstract class Shape {

    private org.newdawn.slick.geom.Shape shapeObject;
    public Shape(org.newdawn.slick.geom.Shape shapeObject){
        this.shapeObject = shapeObject;
    }
    public org.newdawn.slick.geom.Shape getShape(){
        return shapeObject;
    }

    public abstract void jump(Mob mob);
    public abstract void attack(Mob mob);
    public abstract void takeDamage(Mob mob, float damage);
}
