package me.jack.LD35.Entity;

import me.jack.LD35.Level.Level;
import me.jack.LD35.Projectile.LaserProjectile;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Created by Jack on 16/04/2016.
 */
public class EntityPlayer extends Entity {

    Image image;

    public EntityPlayer(float x, float y) {
        super(x, y, 16, 16);
        image = sprites.getSprite(0, 0);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, getX(), getY());
    }

    @Override
    public void update(Level level) {
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            if(level.canMove(getX(),getY()-6,getW(),getH()))
            addY(-6);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            if(level.canMove(getX(),getY()+6,getW(),getH()))
            addY(6);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            if(level.canMove(getX()-6,getY(),getW(),getH()))
            addX(-6);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            if(level.canMove(getX()+6,getY(),getW(),getH()))
            addX(6);
        }
    }

    public void mouseClick(int x,int y, int button,Level level){
        level.entities.add(new EntityProjectile(getX(),getY(),x,y,new LaserProjectile()));
    }

}
