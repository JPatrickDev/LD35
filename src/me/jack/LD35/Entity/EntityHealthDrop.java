package me.jack.LD35.Entity;

import me.jack.LD35.Level.Level;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by Jack on 17/04/2016.
 */
public class EntityHealthDrop extends Entity {

    public static Image image;

    public EntityHealthDrop(float x, float y) {
        super(x, y, 16,16);
        if(image == null)
            try {
                image = new Image("res/healthDrop.png");
            } catch (SlickException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image,getX(),getY());
    }

    @Override
    public void update(Level level) {
        EntityPlayer p = level.getPlayer();
        Rectangle pR = new Rectangle((int)p.getX(),(int)p.getY(),p.getW(),p.getH());
        Rectangle me = new Rectangle((int)getX(),(int)getY(),getW()*2,getH()*2);
        if(pR.intersects(me)){
            level.entities.remove(this);
            p.health+=5;
        }
    }
}
