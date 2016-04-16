package me.jack.LD35.Entity;

import me.jack.LD35.Level.Level;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by Jack on 16/04/2016.
 */
public class EntityRobot extends Entity{

    Image image;

    public EntityRobot(float x, float y) {
        super(x, y, 16, 16);
        image = sprites.getSprite(3, 0);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, getX(), getY());
    }

    @Override
    public void update(Level level) {
        EntityPlayer p = level.getPlayer();
        float xSpeed = (p.getX() - getX());
        float ySpeed = (p.getY() - getY());
        float factor = (float) (2 / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
        xSpeed *= factor;
        ySpeed *= factor;
        if(!Float.isNaN(xSpeed))
            addX(xSpeed);
        if(!Float.isNaN(ySpeed))
            addY(ySpeed);



        Rectangle rr  = new Rectangle(getX(),getY(),16,16);

        for(Entity e : level.entities){
            if(e instanceof EntityProjectile){
                Rectangle r = new Rectangle(e.getX(),e.getY(),16,16);
                if(r.intersects(rr))
                    ((EntityProjectile) e).hitEntity(this);
            }
        }
        if(health<=0)
            level.entities.remove(this);
    }
}
