package me.jack.LD35.Entity;

import me.jack.LD35.Level.Level;
import me.jack.LD35.Level.Tile.Tile;
import me.jack.LD35.Particles.ExplosionParticle;
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

    static SpriteSheet blueRobot, greenRobot;
    Animation fireAnim;
    public boolean onFire = false;
    int type = 0;

    Image[] images = new Image[4];

    public EntityRobot(float x, float y) {
        super(x, y, 16, 16);
        try {
            blueRobot = new SpriteSheet(new Image("res/blueRobot.png"),16,16);
            greenRobot = new SpriteSheet(new Image("res/greenRobot.png"),16,16);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        if (fireAnim == null)
            try {
                fireAnim = new Animation(new SpriteSheet("res/fireAnimation.png", 16, 16), 150);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        Random r = new Random();
        if (r.nextInt(5) == 0)
            type = 1;

        if(type == 0){
            images[0] = blueRobot.getSprite(0,0);
            images[1] = blueRobot.getSprite(0,1);

            images[2] = blueRobot.getSprite(1,0);
            images[3] = blueRobot.getSprite(1,1);
        }else{
            images[0] = greenRobot.getSprite(0,0);
            images[1] = greenRobot.getSprite(0,1);

            images[2] = greenRobot.getSprite(1,0);
            images[3] = greenRobot.getSprite(1,1);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(images[imgPos],getX(),getY());
        if (onFire) {
            fireAnim.draw(getX(), getY());
        }
    }

    int walkingX = -1, walkingY;
    Random r = new Random();

    int imgPos = 0;
    @Override
    public void update(Level level) {
        if (health <= 0) {
            level.entities.remove(this);
            level.getPlayer().chargeLevel++;
            level.getPlayer().addExp(0.5f);
            if (new Random().nextInt(10) == 0)
                level.entities.add(new EntityHealthDrop(getX(), getY()));
            return;
        }
        EntityPlayer p = level.getPlayer();
        float xSpeed = (p.getX() - getX());
        float ySpeed = (p.getY() - getY());


        if (type == 0) {

        } else {
            if (walkingX == -1) {
                walkingX = r.nextInt(level.width - 2) + 1;
                walkingY = r.nextInt(level.height - 2) + 1;
                walkingX *= Tile.TILE_SIZE;
                walkingY *= Tile.TILE_SIZE;
            } else {
                if (r.nextInt(20) == 0) {
                    walkingX = r.nextInt(level.width - 2) + 1;
                    walkingY = r.nextInt(level.height - 2) + 1;
                    walkingX *= Tile.TILE_SIZE;
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

        if(Math.abs(xSpeed) > Math.abs(ySpeed)){
                if(xSpeed > 0){
                    imgPos = 3;
                }else{
                    imgPos = 2;
                }
        }else{
            if(ySpeed > 0){
                imgPos = 0;
            }else{
                imgPos = 1;
            }
        }

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
            if (par instanceof ExplosionParticle) {
                if (rr.intersects(par.getHitbox())) {
                    health -= 5;
                    par.remove = true;
                    return;
                }
            }
        }

        if (onFire)
            health -= 0.1f;

        Rectangle eRectangle = new Rectangle((int) getX(), (int) getY(), 16, 16);
        Rectangle pRectangle = new Rectangle((int) level.getPlayer().getX(), (int) level.getPlayer().getY(), 16, 16);
        if (eRectangle.intersects(pRectangle) && System.currentTimeMillis() - lastAttack >= coolDown) {
            level.getPlayer().health -= 0.5;
            lastAttack = System.currentTimeMillis();
        }

        if (type == 1) {
            if (r.nextInt(600) == 0) {
                level.entities.add(new EntityProjectile(getX(), getY(), (int) p.getX(), (int) p.getY(), new GreenProjectile()));
            }
        }


    }

    long lastAttack = 0;
    long coolDown = 100;
}
