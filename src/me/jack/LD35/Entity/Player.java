package me.jack.LD35.Entity;

import me.jack.LD35.Level.Level;
import me.jack.LD35.Projectile.LaserProjectile;
import me.jack.LD35.Shape.Circle;
import me.jack.LD35.Shape.Shape;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 16/04/2016.
 */
public class Player extends Mob{

    public Player(int x, int y) {
        super(x, y);
        currentHitbox.setX(x);
        currentHitbox.setY(y);
    }

    Shape currentShape = new Circle();

    org.newdawn.slick.geom.Shape currentHitbox = currentShape.getShape();

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fill(currentHitbox);
        g.setColor(Color.white);
    }


    @Override
    public void update(Level level){
        super.update(level);
        if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
            if(level.canMove(getX()+8,getY()-1,16,16) == null) {
                addX(8);
            }
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
            if(level.canMove(getX()-8,getY()-1,16,16) == null) {
                addX(-8);
            }
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            if(level.canMove(getX(),getY()-8,16,16) == null) {
                addY(-8);
            }
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            if(level.canMove(getX(),getY()+8,16,16) == null) {
                addY(+8);
            }
        }

        currentHitbox.setX(getX());
        currentHitbox.setY(getY());
    }



    public void shift(Shape newShape){
        currentShape = newShape;
        currentHitbox = currentShape.getShape();
    }

    public void attack(Level level,int x,int y) {
      level.entities.add(new EntityProjectile(getX(),getY(),x,y,new LaserProjectile()));
    }

}
