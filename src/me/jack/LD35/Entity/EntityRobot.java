package me.jack.LD35.Entity;

import me.jack.LD35.Level.Level;
import me.jack.LD35.Level.Tile.Tile;
import me.jack.LD35.Particles.FireParticle;
import me.jack.LD35.Projectile.GreenProjectile;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import uk.co.jdpatrick.JEngine.Particle.Particle;

import java.util.Random;

/**
 * Created by Jack on 16/04/2016.
 */
public class EntityRobot extends Entity {

    static Image blueRobot, greenRobot;
    Animation fireAnim;
    public boolean onFire = false;
    int type = 0;

    public EntityRobot(float x, float y) {
        super(x, y, 16, 16);
        blueRobot = sprites.getSprite(3, 0);
        greenRobot = sprites.getSprite(4, 0);
        if (fireAnim == null)
            try {
                fireAnim = new Animation(new SpriteSheet("res/fireAnimation.png", 16, 16), 150);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        Random r= new Random();
        if(r.nextInt(5) == 0)
            type = 1;
    }

    @Override
    public void render(Graphics g) {
        if (type == 0)
            g.drawImage(blueRobot, getX(), getY());
        else
            g.drawImage(greenRobot, getX(), getY());
        if (onFire) {
            fireAnim.draw(getX(), getY());
        }
    }

    int walkingX = -1,walkingY;
    Random r = new Random();
    @Override
    public void update(Level level) {
        EntityPlayer p = level.getPlayer();
        float xSpeed = (p.getX() - getX());
        float ySpeed = (p.getY() - getY());


        if(type == 0) {

        }else{
            if(walkingX == -1){
                walkingX = r.nextInt(level.width-2) + 1;
                walkingY = r.nextInt(level.height-2) + 1;
                walkingX*= Tile.TILE_SIZE;
                walkingY *= Tile.TILE_SIZE;
            }else{
                if(r.nextInt(20) == 0){
                    walkingX = r.nextInt(level.width-2) + 1;
                    walkingY = r.nextInt(level.height-2) + 1;
                    walkingX*= Tile.TILE_SIZE;
                    walkingY *= Tile.TILE_SIZE;
                }
            }
             xSpeed = (walkingX - getX());
             ySpeed = (walkingY - getY());

        }
        float factor = (float) (2 / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
        xSpeed *= factor;
        ySpeed *= factor;
        if (!Float.isNaN(xSpeed))
            addX(xSpeed);
        if (!Float.isNaN(ySpeed))
            addY(ySpeed);
        Rectangle rr = new Rectangle(getX(), getY(), 16, 16);

        for (Entity e : level.entities) {
            if (e instanceof EntityProjectile) {
                Rectangle r = new Rectangle(e.getX(), e.getY(), 16, 16);
                if (r.intersects(rr))
                    ((EntityProjectile) e).hitEntity(this, level);
            }
        }
        for (Particle par : level.particleSystem.getParticles()) {
            if (par instanceof FireParticle) {
                if (rr.intersects(par.getHitbox())) {
                    onFire = true;
                }
            }
        }
        if (health <= 0) {
            level.entities.remove(this);
            level.getPlayer().chargeLevel++;
            level.getPlayer().addExp(0.5f);
            if (new Random().nextInt(10) == 0)
                level.entities.add(new EntityHealthDrop(getX(), getY()));
        }
        if (onFire)
            health -= 0.1f;

        Rectangle eRectangle = new Rectangle((int) getX(), (int) getY(), 16, 16);
        Rectangle pRectangle = new Rectangle((int) level.getPlayer().getX(), (int) level.getPlayer().getY(), 16, 16);
        if (eRectangle.intersects(pRectangle) && System.currentTimeMillis() - lastAttack >= coolDown) {
            level.getPlayer().health -= 0.5;
            lastAttack = System.currentTimeMillis();
        }

        if(type == 1){
            if(r.nextInt(200) == 0){
                level.entities.add(new EntityProjectile(getX(),getY(),(int)p.getX(),(int)p.getY(),new GreenProjectile()));
            }
        }


    }

    long lastAttack = 0;
    long coolDown = 100;
}
