package me.jack.LD35.Entity;

import me.jack.LD35.Level.Level;
import me.jack.LD35.Particles.FireParticle;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import uk.co.jdpatrick.JEngine.Particle.Particle;

/**
 * Created by Jack on 16/04/2016.
 */
public class EntityRobot extends Entity{

    Image image;

    public boolean onFire = false;
    public EntityRobot(float x, float y) {
        super(x, y, 16, 16);
        image = sprites.getSprite(3, 0);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, getX(), getY());
        if(onFire){
            g.setColor(Color.red);
            g.fillRect(getX(),getY(),getW(),getH());
            g.setColor(Color.white);
        }
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
                    ((EntityProjectile) e).hitEntity(this,level);
            }
        }
        for(Particle par :level.particleSystem.getParticles()){
                if(par instanceof FireParticle){
                    if(rr.intersects(par.getHitbox())){
                        onFire = true;
                    }
                }
        }
        if(health<=0) {
            level.entities.remove(this);
            level.getPlayer().chargeLevel++;
            level.getPlayer().addExp(0.5f);
        }
        if(onFire)
            health-=0.5f;
    }
}
